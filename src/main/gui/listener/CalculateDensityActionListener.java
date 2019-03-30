package main.gui.listener;

import main.gui.GuiBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculateDensityActionListener implements ActionListener {
    private GuiBuilder guiBuilder;
    private static final String LESS_THAN_MIN_POINTS = "Количество точек в системе координат меньше чем заданый параметр минимального числа точек в окружности %s";

    private String getErrorMessageWithMinPointsCount() {
        return String.format(LESS_THAN_MIN_POINTS, this.guiBuilder.getMinPointsInCircle());
    }

    public CalculateDensityActionListener(GuiBuilder guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.guiBuilder.getCoordinateSystem().getPoints().size() < this.guiBuilder.getMinPointsInCircle()) {
            JOptionPane.showConfirmDialog(null, this.getErrorMessageWithMinPointsCount(), "Ошибка вычисления", JOptionPane.DEFAULT_OPTION);

        } else {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

//                    guiBuilder.getProgressBar().setIndeterminate(true);
                    guiBuilder.setEnabledForButtons(false);
                    guiBuilder.getDrawPanel().calculateDensityAndDrawCircle(guiBuilder);
//                    guiBuilder.getProgressBar().setIndeterminate(false);
                    guiBuilder.setEnabledForButtons(true);
                }

            });
            thread.start();
        }

    }
}
