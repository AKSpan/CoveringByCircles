package main.gui.listener;

import main.gui.GuiBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopOperationActionListener implements ActionListener {
    private GuiBuilder guiBuilder;

    public StopOperationActionListener(GuiBuilder guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.guiBuilder.setStopAction(true);
    }
}
