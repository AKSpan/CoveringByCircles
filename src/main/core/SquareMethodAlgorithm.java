package main.core;

import main.core.math.GetCircleOutSquare;
import main.core.math.SquareBlockFactory;
import main.core.objects.Circle;
import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.core.objects.ResultInfo;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * Алгоритм для метода квадрантов
 */
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
    public ResultInfo calculateDensityAndDrawCircle(GuiBuilderV2 guiBuilder) {
        CoordinateSystem coordinateSystem = guiBuilder.getLogicWrapper().getCoordinateSystem();
        long minPointInCircle = guiBuilder.getLogicWrapper().getMinimalPointInCircle();
        HashSet<Point> points = coordinateSystem.getPoints();
        ResultInfo rs = new ResultInfo();


        if (this.getMinimalPointsInside() > coordinateSystem.getPoints().size())
            JOptionPane.showConfirmDialog(null, "Число точек в системе координат [" + coordinateSystem.getPoints().size() + "] меньше,\n" +
                    "чем минимально необходимо для работы данного алгоритма [" + this.getMinimalPointsInside() + "]", "Ошибка", JOptionPane.DEFAULT_OPTION);
        else {
            GetCircleOutSquare getCircleOutSquare = new GetCircleOutSquare(
                    new SquareBlockFactory(
                            coordinateSystem,
                            guiBuilder.getLogicWrapper().getSquareDeepValue(),
                            (Graphics2D) guiBuilder.getUiComponentsHolder().getDrawPanel().getGraphics(),
                            guiBuilder),
                    minPointInCircle);

            Circle circle = getCircleOutSquare.getCircle();
            rs.setSquareBlocks(Collections.singletonList(getCircleOutSquare.getSquareBlock()));
            List<Point> pointInside = new ArrayList<>();
            for (Point point : points) {
                if (circle.isPointInCircle(point))
                    pointInside.add(point);
            }

            rs.setCircle(circle);
            rs.setPoints(pointInside);

        }
        return rs;
    }

    @Override
    public int getMinimalPointsInside() {
        return 2;
    }

    @Override
    public void setTextToLabel(String labelText) {

    }
}
