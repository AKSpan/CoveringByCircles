package main;

import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.util.Random;

public class MainV2 {

    public static void main(String[] args) {
        GuiBuilderV2 guiBuilder = new GuiBuilderV2();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                guiBuilder.build().show();
            }
        });
    }
}
