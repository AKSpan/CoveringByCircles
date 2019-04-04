package main.gui.listener;

import main.core.objects.CoordinateSystem;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetSquareDeepValueActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;

    public SetSquareDeepValueActionListener(GuiBuilderV2 guiBuilderV2) {
        this.guiBuilder = guiBuilderV2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String deepValue = JOptionPane.showInputDialog(null, "Укажите значение параметра", "Глубина разбиения на квадраты:", JOptionPane.INFORMATION_MESSAGE);
        try {
            if (deepValue != null) {
                int i = Integer.parseInt(deepValue);
                this.guiBuilder.getLogicWrapper().setSquareDeepValue(i);
            }
        } catch (NumberFormatException e1) {
            JOptionPane.showConfirmDialog(null, e1.getMessage(), "Ошибка", JOptionPane.DEFAULT_OPTION);
        }


    }
}
