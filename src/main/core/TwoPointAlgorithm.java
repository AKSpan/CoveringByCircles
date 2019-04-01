package main.core;

import main.core.math.Permutations;
import main.core.objects.*;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.math.BigInteger;
import java.util.*;

public class TwoPointAlgorithm implements IAlgorithm {
    @Override
    public ResultInfo calculateDensityAndDrawCircle(GuiBuilderV2 guiBuilder) {
        guiBuilder.getLogicWrapper().setStopAction(false);
        CoordinateSystem coordinateSystem = guiBuilder.getLogicWrapper().getCoordinateSystem();
        JLabel label = guiBuilder.getUiComponentsHolder().getOperationLabel();
        long minPointInCircle = guiBuilder.getLogicWrapper().getMinimalPointInCircle();
        HashSet<Point> points = coordinateSystem.getPoints();
        Point[] objects = points.toArray(new Point[]{});
        Point p1, p2;
        ResultInfo resultInfo = new ResultInfo();
        List<List<Point>> listOfPoints = new ArrayList<>();
        BigInteger maxOperationCount = Permutations.get(points.size(), 2);
        int currentOperationCount = 0;
        for (Point objPoint1 : objects) {
            if (guiBuilder.getLogicWrapper().isStopAction())
                break;
            p1 = objPoint1;
            for (Point objPoint2 : objects) {
                if (guiBuilder.getLogicWrapper().isStopAction())
                    break;
                if (p1.equals(objPoint2)) {
                    continue;
                }
                currentOperationCount++;
                label.setText(String.format("Шаг операции %s из максимальных %s", currentOperationCount, maxOperationCount));
                p2 = objPoint2;
                boolean canPointAdd = true;
                for (List<Point> pointList : listOfPoints) {
                    if (guiBuilder.getLogicWrapper().isStopAction())
                        break;
                    int equalsCounter = 0;
                    for (Point point : pointList) {
                        if (guiBuilder.getLogicWrapper().isStopAction())
                            break;
//                        currentOperationCount++;
//                        label.setText(String.format("Шаг операции %s из максимальных %s", currentOperationCount, maxOperationCount));

                        if (point.equals(p1) || point.equals(p2))
                            equalsCounter++;
                    }
                    if (equalsCounter == 2) {
                        canPointAdd = false;
                        break;
                    }

                }
                if (canPointAdd)
                    listOfPoints.add(Arrays.asList(p1, p2));
            }
        }
        List<Circle> circlesByPoints = this.getCirclesByPoints(listOfPoints);
        Map<Circle, List<Point>> circlesWithPointsInside = this.getCirclesWithPointsInside(circlesByPoints, points, minPointInCircle);
        Density maxDensity = this.getMaxDensity(circlesWithPointsInside, minPointInCircle);
        System.out.println(maxDensity);
        resultInfo.setAlgorithmNameLabel(this.getAlgorithmName());
        coordinateSystem.setCircle(maxDensity.getCircle());

        resultInfo.setCircle(maxDensity.getCircle());
        resultInfo.setPoints(maxDensity.getPoints());
        return resultInfo;
    }

    private List<Circle> getCirclesByPoints(List<List<Point>> points) {
        List<Circle> circles = new ArrayList<>();
        for (List<Point> point : points) {
            circles.add(this.getCircle(point, 2));
        }
        return circles;
    }


    public double getCircleRadius(List<Point> coordinates) {
        Point pa = coordinates.get(0);
        Point pb = coordinates.get(1);
        return getSide(pa, pb) / 2;
    }


    @Override
    public String getAlgorithmName() {
        return "Две точки";
    }


}
