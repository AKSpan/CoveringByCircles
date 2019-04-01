package main.gui.listener;

import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class ClearFormActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;

    public ClearFormActionListener(GuiBuilderV2 guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        guiBuilder.getUiComponentsHolder().getDrawPanel().addPoints(Collections.emptySet(), null);
//        guiBuilder.getUiComponentsHolder().getCoordinateSystem().clear();
//        guiBuilder.getUiComponentsHolder().getTextField().setText("0");
    }
}
