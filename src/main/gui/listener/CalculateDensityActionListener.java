package main.gui.listener;

import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateDensityActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;
    private static final String LESS_THAN_MIN_POINTS = "Количество точек в системе координат меньше чем заданый параметр минимального числа точек в окружности %s";

    private String getErrorMessageWithMinPointsCount() {
        return String.format(LESS_THAN_MIN_POINTS, this.guiBuilder);
    }

    public CalculateDensityActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("CalculateDensityActionListener");
        if (this.guiBuilder.getLogicWrapper().getCoordinateSystem().getPoints().size() < this.guiBuilder.getLogicWrapper().getMinimalPointInCircle()) {
            JOptionPane.showConfirmDialog(null, this.getErrorMessageWithMinPointsCount(), "Ошибка вычисления", JOptionPane.DEFAULT_OPTION);

        } else {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    guiBuilder.getUiComponentsHolder().setEnabledForButtons(false);
                    guiBuilder.getUiComponentsHolder().getDrawPanel().calculateDensityAndDrawCircle(guiBuilder);
                    guiBuilder.getUiComponentsHolder().setEnabledForButtons(true);
                }

            });
            thread.start();
        }

    }
}
