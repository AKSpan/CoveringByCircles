package main.gui.listener;

import main.core.objects.CoordinateSystem;
import main.gui.GuiBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateButtonActionListener implements ActionListener {
    private GuiBuilder guiBuilder;

    public GenerateButtonActionListener(GuiBuilder guiBuilder) {
        this.guiBuilder = guiBuilder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.guiBuilder.setStopAction(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String text = GenerateButtonActionListener.this.guiBuilder.getTextField().getText();

                //        guiBuilder.getProgressBar().setIndeterminate(true);
                int count = Integer.parseInt(text);
                System.out.println(count);
                guiBuilder.getSystemCoordinateGenerator().setPointCount(count);
                CoordinateSystem coordinateSystem = guiBuilder.getSystemCoordinateGenerator().generate(GenerateButtonActionListener.this.guiBuilder);
                guiBuilder.setCoordinateSystem(coordinateSystem);
                guiBuilder.getDrawPanel().addPoints(coordinateSystem, null);
                guiBuilder.getTextField().setText(String.valueOf(coordinateSystem.getPoints().size()));
//        guiBuilder.getProgressBar().setIndeterminate(false);
            }
        }).start();


    }
}
