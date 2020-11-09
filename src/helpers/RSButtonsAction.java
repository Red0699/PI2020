/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import RSMaterialComponent.RSButtonIconOne;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.ButtonModel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RSButtonsAction extends AbstractCellEditor implements TableCellEditor {

    private final RSRenderButtonsTable panel = new RSRenderButtonsTable();
    private final JTable table;
    private Object o;

    private class EditingStopHandler extends MouseAdapter implements ActionListener {

        @Override
        public void mousePressed(MouseEvent e) {
            Object o = e.getSource();
            if (o instanceof TableCellEditor) {
                actionPerformed(null);
            } else if (o instanceof RSButtonIconOne) {
                ButtonModel m = ((RSButtonIconOne) e.getComponent()).getModel();
                if (m.isPressed() && table.isRowSelected(table.getEditingRow()) && e.isControlDown()) {
                    panel.setBackground(table.getBackground());
                }
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    fireEditingStopped();
                }
            });
        }
    }

    public RSButtonsAction(JTable table, int columnAction, Object[] params) {
        super();
        this.table = table;
        
         RSObjectArray datos = new RSObjectArray();
        datos.add("clase", params[0]);
        datos.add("metodoView", params[1]);
        datos.add("metodoEdit", params[2]);

        panel.buttons.get(0).setAction(new ViewAction(table, columnAction, datos));
        panel.buttons.get(1).setAction(new EditAction(table, columnAction, datos));

        EditingStopHandler handler = new EditingStopHandler();
        panel.buttons.stream().map((b) -> {
            b.addMouseListener(handler);
            return b;
        }).forEachOrdered((b) -> {
            b.addActionListener(handler);
        });
        panel.addMouseListener(handler);
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column) {
        panel.setBackground(table.getSelectionBackground());
        panel.updateButtons(value);
        o = value;
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return o;
    }
}

class ViewAction extends AbstractAction {

    private final JTable table;
    private final int columnAction;
    private final RSObjectArray params;

    public ViewAction(JTable table, int columnAction, RSObjectArray params) {
        super(ActionsButtonsTable.Ver.toString());
        this.table = table;
        this.columnAction = columnAction;
        this.params = params;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int row = table.convertRowIndexToModel(table.getEditingRow());
            Object valor = table.getModel().getValueAt(row, this.columnAction);

            Class c = Class.forName(this.params.getValue("clase").toString());
            Object o = c.newInstance();
            Class[] paramTypes = new Class[1];
            paramTypes[0] = Object.class;
            String methodName = this.params.getValue("metodoView").toString();
            Method m = c.getDeclaredMethod(methodName, paramTypes);
            m.invoke(o, valor);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ViewAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class EditAction extends AbstractAction {

    private final JTable table;
    private final int columnAction;
    private final RSObjectArray params;

    public EditAction(JTable table, int columnAction, RSObjectArray params) {
        super(ActionsButtonsTable.Ver.toString());
        this.table = table;
        this.columnAction = columnAction;
        this.params = params;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int row = table.convertRowIndexToModel(table.getEditingRow());
            Object valor = table.getModel().getValueAt(row, this.columnAction);

            Class c = Class.forName(this.params.getValue("clase").toString());
            Object o = c.newInstance();
            Class[] paramTypes = new Class[1];
            paramTypes[0] = Object.class;
            String methodName = this.params.getValue("metodoEdit").toString();
            Method m = c.getDeclaredMethod(methodName, paramTypes);
            m.invoke(o, valor);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ViewAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}