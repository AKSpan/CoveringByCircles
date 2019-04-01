package main.gui.listener;

import main.core.*;
import main.core.enums.AlgorithmTypes;
import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChosenAlgorithmActionListener implements ActionListener {
    private GuiBuilderV2 guiBuilder;
    private AlgorithmTypes algorithm;

    public ChosenAlgorithmActionListener(GuiBuilderV2 guiBuilder, AlgorithmTypes algorithm) {
        this.guiBuilder = guiBuilder;
        this.algorithm = algorithm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (this.algorithm) {
            case FULL_SEARCH:
                guiBuilder.getLogicWrapper().setAlgorithm(new FullSearchAlgorithm());
                break;
            case TWO_POINTS:
                guiBuilder.getLogicWrapper().setAlgorithm(new TwoPointAlgorithm());

                break;
            case THREE_POINTS:
                guiBuilder.getLogicWrapper().setAlgorithm(new ThreePointsAlgorithm());

                break;
            case SQUARES_METHOD:
                guiBuilder.getLogicWrapper().setAlgorithm(new SquareMethodAlgorithm());

                break;
        }
    }
}
