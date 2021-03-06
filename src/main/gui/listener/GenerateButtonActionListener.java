package main.gui.listener;

import main.core.ThreadFactory;
import main.core.objects.CoordinateSystem;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Класс для реализации события генерации точек
 */
public class GenerateButtonActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;

    public GenerateButtonActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("GenerateButtonActionListener");
        this.guiBuilder.getLogicWrapper().setStopAction(false);
        String stringPointCount = JOptionPane.showInputDialog(null, "Укажите значение параметра", "Количество точек для генерации:", JOptionPane.INFORMATION_MESSAGE);
        try {
            if (stringPointCount != null) {
                int i = Integer.parseInt(stringPointCount);
                this.guiBuilder.getLogicWrapper().getSystemCoordinateGenerator().setPointCount(i);
                this.guiBuilder.getExecutorServiceWrapper().execute(ThreadFactory.generatePoint(this.guiBuilder));

            }
        } catch (NumberFormatException e1) {
            JOptionPane.showConfirmDialog(null, e1.getMessage(), "Ошибка", JOptionPane.DEFAULT_OPTION);

        }


    }
}
