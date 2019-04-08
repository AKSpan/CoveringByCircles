package main.gui.listener;

import main.core.enums.AlgorithmTypes;
import main.gui.GuiBuilderV2;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Класс обертка для удобного хранения всех классов-событий приложения
 */
public class ApplicationActionListenerHolder {
    private GuiBuilderV2 guiBuilderV2;

    public ApplicationActionListenerHolder(GuiBuilderV2 guiBuilderV2) {
        this.guiBuilderV2 = guiBuilderV2;
    }

    public ActionListener getChosenAlgorithmActionListener( AlgorithmTypes algorithmTypes) {
        return new ChosenAlgorithmActionListener(this.guiBuilderV2, algorithmTypes);
    }

    public ActionListener getClearFormActionListener() {
        return new ClearFormActionListener(this.guiBuilderV2);
    }
    public ActionListener getCalculateDensityActionListener() {
        return new CalculateDensityActionListener(this.guiBuilderV2);
    }
    public ActionListener getGeneratePointsActionListener() {
        return new GenerateButtonActionListener(this.guiBuilderV2);
    }
    public ActionListener getLoadFromFileActionListener() {
        return new LoadButtonActionListener(this.guiBuilderV2);
    }
    public ActionListener getSaveToFileActionListener() {
        return new SaveButtonActionListener(this.guiBuilderV2);
    }
    public KeyListener getSetMinimalPointsInCircleActionListener() {
        return new SetMinimalPointInCircleActionListener(this.guiBuilderV2);
    }
    public ActionListener getStopOperationActionListener() {
        return new StopOperationActionListener(this.guiBuilderV2);
    }

    public KeyListener getSetSquareDeepValue() {
        return new SetSquareDeepValueActionListener(this.guiBuilderV2);
    }

    public GuiBuilderV2 getGuiBuilder() {
        return guiBuilderV2;
    }
}
