package main.gui.listener;

import main.gui.GuiBuilderV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Класс для реализации события остановки любых вычислний в приложении
 */
public class StopOperationActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;

    public StopOperationActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.guiBuilder.getLogicWrapper().setStopAction(true);
    }
}
