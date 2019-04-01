package main.gui.listener;

import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetMinimalPointInCircleActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;

    public SetMinimalPointInCircleActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = JOptionPane.showInputDialog(null, "Укажите значение параметра", "Минимальное число точек в окружности:",JOptionPane.INFORMATION_MESSAGE);
        try {
            if (s != null) {
                long i = Long.parseLong(s);
//                this.guiBuilder.setMinPointsInCircle(i);
            }
        } catch (NumberFormatException e1) {
            JOptionPane.showConfirmDialog(null, e1.getMessage(), "Ошибка", JOptionPane.DEFAULT_OPTION);

        }
    }
}
