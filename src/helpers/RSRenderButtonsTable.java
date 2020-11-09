/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import RSMaterialComponent.RSButtonIconOne;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.swing.JPanel;
import rojeru_san.efectos.ValoresEnum;

enum ActionsButtonsTable {
    Ver, Editar;
}

public class RSRenderButtonsTable extends JPanel {

    public final List<RSButtonIconOne> buttons = new ArrayList<>();

    public RSRenderButtonsTable() {
        super(new FlowLayout(FlowLayout.LEFT));
        setOpaque(true);
        for (ActionsButtonsTable a : ActionsButtonsTable.values()) {
            RSButtonIconOne b = new RSButtonIconOne();
            b.setPreferredSize(new Dimension(35, 35));
            b.setRound(10);
            b.setSizeIcon(25f);
            if (a.toString().equals("Ver")) {
                b.setBackground(new Color(255, 0, 0));
                b.setIcons(ValoresEnum.ICONS.INFO);
                b.setToolTipText("Ver información");
            }
            if (a.toString().equals("Editar")) {
                b.setBackground(new Color(0,204,204));
                b.setIcons(ValoresEnum.ICONS.EDIT);
                b.setToolTipText("Editar información");
            }
            b.setFocusable(false);
            b.setRolloverEnabled(false);
            add(b);
            buttons.add(b);
        }
    }

    public void updateButtons(Object value) {
        if (value instanceof EnumSet) {
            EnumSet ea = (EnumSet) value;
            removeAll();
            if (ea.contains(ActionsButtonsTable.Ver)) {
                add(buttons.get(0));
            }
            if (ea.contains(ActionsButtonsTable.Editar)) {
                add(buttons.get(1));
            }
        }
    }
}
