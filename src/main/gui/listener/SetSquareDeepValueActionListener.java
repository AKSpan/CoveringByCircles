package main.gui.listener;

import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SetSquareDeepValueActionListener implements KeyListener {
    private GuiBuilderV2 guiBuilder;

    public SetSquareDeepValueActionListener(GuiBuilderV2 guiBuilderV2) {
        this.guiBuilder = guiBuilderV2;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String deepValue = this.guiBuilder.getUiComponentsHolder().getSquareDeepValue().getText();
        try {
            int i = this.guiBuilder.getLogicWrapper().getAlgorithm().getSquareDeepValue();
            System.out.println("i>>>"+i);
            if (deepValue != null && !deepValue.isEmpty())
                i = Integer.parseInt(deepValue);
            this.guiBuilder.getLogicWrapper().setSquareDeepValue(i);
        } catch (NumberFormatException e1) {
            JOptionPane.showConfirmDialog(null, e1.getMessage(), "Ошибка", JOptionPane.DEFAULT_OPTION);
        }
    }
}
