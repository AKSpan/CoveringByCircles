package main.core;

import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.core.objects.ResultInfo;
import main.gui.GuiBuilderV2;

import java.util.HashSet;
import java.util.List;

public class FullSearchAlgorithm implements IAlgorithm {
    @Override
    public String getAlgorithmName() {
        return "Полный перебор";
    }


    @Override
    public double getCircleRadius(List<Point> coordinates) {
        return 0;
    }

    @Override
    public ResultInfo calculateDensityAndDrawCircle(GuiBuilderV2 guiBuilder)
    {
        CoordinateSystem coordinateSystem = guiBuilder.getLogicWrapper().getCoordinateSystem();
        long minPointInCircle = guiBuilder.getLogicWrapper().getMinimalPointInCircle();
        HashSet<Point> points = coordinateSystem.getPoints();
        Point[] objects = points.toArray(new Point[]{});

        return new ResultInfo();
    }

    @Override
    public int getMinimalPointsInside() {
        return 2;
    }
}
