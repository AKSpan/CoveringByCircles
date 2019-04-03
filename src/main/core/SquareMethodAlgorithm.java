package main.core;

import main.core.math.GetCircleOutSquare;
import main.core.math.SquareBlockFactory;
import main.core.objects.Circle;
import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.core.objects.ResultInfo;
import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SquareMethodAlgorithm implements IAlgorithm {
    private final static int SQUARE_DEEP = 16;//степень 2ки

    @Override
    public String getAlgorithmName() {
        return "Метод квадратнов";
    }


    @Override
    public double getCircleRadius(List<Point> coordinates) {
        return 0;
    }

    @Override
    public ResultInfo calculateDensityAndDrawCircle(GuiBuilderV2 guiBuilder) {
        CoordinateSystem coordinateSystem = guiBuilder.getLogicWrapper().getCoordinateSystem();
        long minPointInCircle = guiBuilder.getLogicWrapper().getMinimalPointInCircle();
        HashSet<Point> points = coordinateSystem.getPoints();

        GetCircleOutSquare getCircleOutSquare = new GetCircleOutSquare(
                new SquareBlockFactory(
                        coordinateSystem.getMaxX(),
                        coordinateSystem.getMaxY(),
                        0,
                        0,
                        points,
                        SQUARE_DEEP),
                minPointInCircle);

        ResultInfo rs = new ResultInfo();
        Circle circle = getCircleOutSquare.getCircle();
        List<Point> pointInside = new ArrayList<>();
        for (Point point : points) {
            if (circle.isPointInCircle(point))
                pointInside.add(point);
        }

        rs.setCircle(circle);
        rs.setPoints(pointInside);


        return rs;
    }
}
