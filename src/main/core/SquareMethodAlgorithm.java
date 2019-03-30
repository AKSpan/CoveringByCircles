package main.core;

import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.core.objects.ResultInfo;
import main.gui.GuiBuilder;

import javax.swing.*;
import java.util.List;

public class SquareMethodAlgorithm implements IAlgorithm {
    @Override
    public String getAlgorithmName() {
        return "Метод квадратнов";
    }


    @Override
    public double getCircleRadius(List<Point> coordinates) {
        return 0;
    }

    @Override
    public ResultInfo calculateDensityAndDrawCircle(GuiBuilder guiBuilder) {
        return new ResultInfo();
    }
}
