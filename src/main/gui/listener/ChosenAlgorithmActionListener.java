package main.gui.listener;

import main.core.*;
import main.core.enums.AlgorithmTypes;
import main.core.enums.LoggerTextTemplates;
import main.gui.GuiBuilderV2;
import main.gui.UILogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Класс для реализации события для выбора алгоритма
 */
public class ChosenAlgorithmActionListener extends AbstractLogger implements ActionListener {
    private GuiBuilderV2 guiBuilder;
    private AlgorithmTypes algorithm;

    public ChosenAlgorithmActionListener(GuiBuilderV2 guiBuilder, AlgorithmTypes algorithm) {
        super(new UILogger(guiBuilder.getUiComponentsHolder()));
        this.guiBuilder = guiBuilder;
        this.algorithm = algorithm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IAlgorithm currentAlgorithm;
        switch (this.algorithm) {
            case FULL_SEARCH:
                currentAlgorithm = new FullSearchAlgorithm();
                this.log(LoggerTextTemplates.chosenAlgorithm(currentAlgorithm));
                guiBuilder.getLogicWrapper().setAlgorithm(currentAlgorithm);
                break;
            case TWO_POINTS:
                currentAlgorithm = new TwoPointAlgorithm();
                this.log(LoggerTextTemplates.chosenAlgorithm(currentAlgorithm));
                guiBuilder.getLogicWrapper().setAlgorithm(currentAlgorithm);

                break;
            case THREE_POINTS:
                currentAlgorithm = new ThreePointsAlgorithm();
                this.log(LoggerTextTemplates.chosenAlgorithm(currentAlgorithm));
                guiBuilder.getLogicWrapper().setAlgorithm(currentAlgorithm);

                break;
            case SQUARES_METHOD:
                currentAlgorithm = new SquareMethodAlgorithm();
                this.log(LoggerTextTemplates.chosenAlgorithm(currentAlgorithm));
                guiBuilder.getLogicWrapper().setAlgorithm(currentAlgorithm);

                break;
        }
    }
}
