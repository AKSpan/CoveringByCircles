package main.gui.listener;

import main.core.objects.CoordinateSystem;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CoordinateSystem coordinateSystem = guiBuilder.getLogicWrapper().getSystemCoordinateGenerator().generate(GenerateButtonActionListener.this.guiBuilder);
                        guiBuilder.getLogicWrapper().setCoordinateSystem(coordinateSystem);
                        guiBuilder.getUiComponentsHolder().getDrawPanel().addPoints(coordinateSystem, null);
                        guiBuilder.getUiComponentsHolder().setLabelText("Точек " + coordinateSystem.getPoints().size());
                    }
                }).start();
            }
        } catch (NumberFormatException e1) {
            JOptionPane.showConfirmDialog(null, e1.getMessage(), "Ошибка", JOptionPane.DEFAULT_OPTION);

        }


    }
}
