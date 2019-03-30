package main.gui.listener;

import main.core.*;
import main.core.enums.AlgorithmTypes;
import main.gui.GuiBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChosenAlgorithmActionListener implements ActionListener {
    private GuiBuilder guiBuilder;
    private AlgorithmTypes algorithm;

    public ChosenAlgorithmActionListener(GuiBuilder guiBuilder, AlgorithmTypes algorithm) {
        this.guiBuilder = guiBuilder;
        this.algorithm = algorithm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.algorithm) {
            case FULL_SEARCH:
                //todo
                guiBuilder.setAlgorithm(new FullSearchAlgorithm());
                break;
            case TWO_POINTS:
                guiBuilder.setAlgorithm(new TwoPointAlgorithm());

                break;
            case THREE_POINTS:
                guiBuilder.setAlgorithm(new ThreePointsAlgorithm());

                break;
            case SQUARES_METHOD:
                guiBuilder.setAlgorithm(new SquareMethodAlgorithm());

                break;
        }
    }
}
