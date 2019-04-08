package main.gui.listener;

import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Класс для реализации события установки значения минимального числа точек для окружности (кол-во точек, которое должно быть в окружности (минимум))
 */
public class SetMinimalPointInCircleActionListener implements KeyListener {
    private GuiBuilderV2 guiBuilder;

    public SetMinimalPointInCircleActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            String s = this.guiBuilder.getUiComponentsHolder().getMinPointInCircleTextArea().getText();
            long i = guiBuilder.getLogicWrapper().getAlgorithm().getMinimalPointsInside();
            if (s != null && !s.isEmpty())
                i = Long.parseLong(s);
            this.guiBuilder.getLogicWrapper().setMinimalPointsInCircle(i);
        } catch (NumberFormatException e1) {
            JOptionPane.showConfirmDialog(null, e1.getMessage(), "Ошибка", JOptionPane.DEFAULT_OPTION);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {


    }
}
