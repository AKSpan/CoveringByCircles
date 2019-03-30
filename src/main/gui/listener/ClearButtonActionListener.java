package main.gui.listener;

import main.gui.GuiBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class ClearButtonActionListener implements ActionListener {
    private GuiBuilder guiBuilder;

    public ClearButtonActionListener(GuiBuilder guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        guiBuilder.getDrawPanel().addPoints(Collections.emptySet(), null);
        guiBuilder.getCoordinateSystem().clear();
        guiBuilder.getTextField().setText("0");
//        guiBuilder.getProgressBar().setValue(0);
    }
}
