package main.gui.listener;

import main.gui.GuiBuilderV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
/**
 * Класс для реализации события очистки формы
 */
public class ClearFormActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;

    public ClearFormActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        guiBuilder.getUiComponentsHolder().getDrawPanel().addPoints(Collections.emptySet(), null);
        guiBuilder.getLogicWrapper().getCoordinateSystem().clear();
        guiBuilder.getUiComponentsHolder().setLabelText("Точек 0");
    }
}
