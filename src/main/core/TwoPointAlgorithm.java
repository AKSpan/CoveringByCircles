package main.core;

import main.core.math.Permutations;
import main.core.objects.*;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.math.BigInteger;
import java.util.*;
/**
 * Алгоритм через построения окружности на основе 2 точек
 */
public class TwoPointAlgorithm implements IAlgorithm {
    private JLabel label;

    @Override
    public ResultInfo calculateDensityAndDrawCircle(GuiBuilderV2 guiBuilder) {
        guiBuilder.getLogicWrapper().setStopAction(false);
        CoordinateSystem coordinateSystem = guiBuilder.getLogicWrapper().getCoordinateSystem();
        label = guiBuilder.getUiComponentsHolder().getOperationLabel();
        long minPointInCircle = guiBuilder.getLogicWrapper().getMinimalPointInCircle();
        ResultInfo resultInfo = new ResultInfo();
        if (this.getMinimalPointsInside() > coordinateSystem.getPoints().size())
            JOptionPane.showConfirmDialog(null, "Число точек в системе координат [" + coordinateSystem.getPoints().size() + "] меньше,\n" +
                    "чем минимально необходимо для работы данного алгоритма [" + this.getMinimalPointsInside() + "]", "Ошибка", JOptionPane.DEFAULT_OPTION);
        else {
            HashSet<Point> points = coordinateSystem.getPoints();
            Point[] objects = points.toArray(new Point[]{});
            Point p1, p2;

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
                    this.setTextToLabel(String.format("Шаг операции %s из максимальных %s", currentOperationCount, maxOperationCount));
                    p2 = objPoint2;
                    boolean canPointAdd = true;
                    for (List<Point> pointList : listOfPoints) {
                        if (guiBuilder.getLogicWrapper().isStopAction())
                            break;
                        int equalsCounter = 0;
                        for (Point point : pointList) {
                            if (guiBuilder.getLogicWrapper().isStopAction())
                                break;

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
            if (maxDensity != null) {
                System.out.println(maxDensity);
                resultInfo.setAlgorithmNameLabel(this.getAlgorithmName());
                coordinateSystem.setCircle(maxDensity.getCircle());

                resultInfo.setCircle(maxDensity.getCircle());
                resultInfo.setPoints(maxDensity.getPoints());
            }
        }
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

    @Override
    public int getMinimalPointsInside() {
        return 2;
    }

    @Override
    public void setTextToLabel(String labelText) {
        if (this.label != null)
            this.label.setText(labelText);
    }

}
