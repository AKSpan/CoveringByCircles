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
        Point[] objects = points.toArray(new Point[]{});

        int maxX = guiBuilder.getLogicWrapper().getCoordinateSystem().getMaxX();
        int maxY = guiBuilder.getLogicWrapper().getCoordinateSystem().getMaxY();
        GetCircleOutSquare getCircleOutSquare = new GetCircleOutSquare(new SquareBlockFactory(coordinateSystem.getMaxX(), coordinateSystem.getMaxY(),
                0, 0, points, SQUARE_DEEP), minPointInCircle);

        ResultInfo rs = new ResultInfo();
        Circle circle = getCircleOutSquare.getCircle();
        List<Point> pointInside = new ArrayList<>();
        for (Point point : points) {
            if (circle.isPointInCircle(point))
                pointInside.add(point);
        }

        rs.setCircle(circle);
        rs.setPoints(pointInside);

       /* System.out.println(String.format("MaxX %d, MaxY %d", maxX, maxY));
        for (int i = 0; i < SQUARE_DEEP - 1; i++) {
            int stepX = (int) (maxX / (Math.pow(2d, i + 1)));
            int stepY = (int) (maxY / (Math.pow(2d, i + 1)));
            int block1StartX = i * stepX;
            int block1StartY = i * stepY;
            int block2StartX = block1StartX + stepX ;
            int block2StartY = block1StartY + stepY;


            System.out.println(String.format("startX %s", block1StartX));
            System.out.println(String.format("startY %s", block1StartY));
            System.out.println(String.format("block2StartX %s", block2StartX));
            System.out.println(String.format("block2StartY %s", block2StartY));

        }*/
        return rs;
    }
}
