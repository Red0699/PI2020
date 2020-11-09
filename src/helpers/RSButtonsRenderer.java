/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package helpers;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class RSButtonsRenderer implements TableCellRenderer {

    private final RSRenderButtonsTable panel = new RSRenderButtonsTable();

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        panel.updateButtons(value);
        return panel;
    }
}