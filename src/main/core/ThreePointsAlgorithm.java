package main.core;

import main.core.math.Permutations;
import main.core.objects.*;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Алгоритм через построения окружности на основе 3 точек
 */
public class ThreePointsAlgorithm implements IAlgorithm {
    private JLabel label;

    @Override
    public ResultInfo calculateDensityAndDrawCircle(GuiBuilderV2 guiBuilder) {
        guiBuilder.getLogicWrapper().setStopAction(false);
        CoordinateSystem coordinateSystem = guiBuilder.getLogicWrapper().getCoordinateSystem();
        ResultInfo resultInfo = new ResultInfo();

        if (this.getMinimalPointsInside() > coordinateSystem.getPoints().size())
            JOptionPane.showConfirmDialog(null, "Число точек в системе координат [" + coordinateSystem.getPoints().size() + "] меньше,\n" +
                    "чем минимально необходимо для работы данного алгоритма [" + this.getMinimalPointsInside() + "]", "Ошибка", JOptionPane.DEFAULT_OPTION);
        else {
            long minPointInCircle = guiBuilder.getLogicWrapper().getMinimalPointInCircle();
            label = guiBuilder.getUiComponentsHolder().getOperationLabel();
            HashSet<Point> points = coordinateSystem.getPoints();

            Point[] objects = points.toArray(new Point[]{});
            Point p1, p2, p3;
            List<List<Point>> completeResult = new ArrayList<>();
            int currentOperationCount = 0;
            BigInteger maxOperationCount = Permutations.get(points.size(), 3);

            for (Point objPoint1 : objects) {
                if (guiBuilder.getLogicWrapper().isStopAction())
                    break;
                p1 = objPoint1;
                for (Point objPoint2 : objects) {
                    if (guiBuilder.getLogicWrapper().isStopAction())
                        break;
                    if (p1.equals(objPoint2))
                        continue;
                    p2 = objPoint2;
                    for (Point objPoint3 : objects) {
                        if (guiBuilder.getLogicWrapper().isStopAction())
                            break;
                        if (p1.equals(objPoint3) || p2.equals(objPoint3) || p1.equals(objPoint2))
                            continue;
                        currentOperationCount++;
                        this.setTextToLabel(String.format("Шаг операции %s из максимальных %s", currentOperationCount, maxOperationCount));
                        p3 = objPoint3;
                        boolean canPointAdd = true;
                    /*for (List<Point> pointList : completeResult) {
                        if (guiBuilder.getLogicWrapper().isStopAction())
                            break;
                        int equalsCounter = 0;
                        for (Point point : pointList) {
                            if (guiBuilder.getLogicWrapper().isStopAction())
                                break;
                            if (point.equals(p1) || point.equals(p2) || point.equals(p3))
                                equalsCounter++;
                        }
                        if (equalsCounter == 3) {
                            canPointAdd = false;
                            break;
                        }

                    }*/
                        if (canPointAdd) {
                            completeResult.add(Arrays.asList(p1, p2, p3));
                        }
                        //имеем 3 точки, строим окружность, считаем плотность
                    }
                }
            }
            List<Circle> circlesByPoints = this.getCirclesByPoints(completeResult);
            Map<Circle, List<Point>> circlesWithPointsInside = new HashMap<>();
            this.setTextToLabel("Обработка полученных окружностей...");

            for (Circle circlesByPoint : circlesByPoints) {
                List<Point> pointsInsideCircle = new ArrayList<>();
                for (Point point : objects) {
                    if (guiBuilder.getLogicWrapper().isStopAction())
                        break;
                    if (this.isPointInCircle(circlesByPoint, point))
                        pointsInsideCircle.add(point);
                }
                if (pointsInsideCircle.size() > 0)
                    circlesWithPointsInside.put(circlesByPoint, pointsInsideCircle);
            }
            Density maxDensity = this.getMaxDensity(circlesWithPointsInside, minPointInCircle);
            if (maxDensity != null) {
                System.out.println(maxDensity);
                resultInfo.setAlgorithmNameLabel(this.getAlgorithmName());
                coordinateSystem.setCircle(maxDensity.getCircle());
                resultInfo.setCircle(maxDensity.getCircle());
                resultInfo.setPoints(maxDensity.getPoints());
                this.setTextToLabel("Окружность найдена!");

            }
        }
        return resultInfo;
    }

    @Override
    public String getAlgorithmName() {
        return "Три точки";
    }

    private List<Circle> getCirclesByPoints(List<List<Point>> points) {
        List<Circle> circles = new ArrayList<>();
        for (List<Point> point : points) {
            circles.add(this.getCircle(point));
        }
        return circles;
    }

    private Circle getCircle(List<Point> points) {
        if (points.size() != 3)
            throw new IllegalArgumentException("Неверное число точек для вычисления центра окружности. Требутеся 3, но передано " + points.size());
        double circleX, circleY, d;
        Point pA = points.get(0);
        Point pB = points.get(1);
        Point pC = points.get(2);
        double delta1, delta2, delta3;
        delta1 = (pB.getPowX() + pB.getPowY() - pC.getPowX() - pC.getPowY());
        delta2 = pC.getPowX() + pC.getPowY() - pA.getPowX() - pA.getPowY();
        delta3 = (pA.getPowX() + pA.getPowY() - pB.getPowX() - pB.getPowY());
        d = 2 * (
                pA.getX() * (pB.getY() - pC.getY()) +
                        pB.getX() * (pC.getY() - pA.getY()) +
                        pC.getX() * (pA.getY() - pB.getY())
        );
        circleX = -1 * (pA.getY() * delta1 +
                pB.getY() * delta2 +
                pC.getY() * delta3

        ) / d;

        circleY = (
                pA.getX() *
                        delta1 +
                        pB.getX() * delta2 +
                        pC.getX() * delta3

        ) / d;
        double radius = getCircleRadius(points);
        return new Circle(new Point(circleX, circleY), radius);
    }


    private List<Double> getSidesOfTriangle(List<Point> coordinates) {
        List<Double> sides = new ArrayList<>(3);
        Point pointA = coordinates.get(0);
        Point pointB = coordinates.get(1);
        Point pointC = coordinates.get(2);
        sides.add(this.getSide(pointA, pointB));
        sides.add(this.getSide(pointA, pointC));
        sides.add(this.getSide(pointB, pointC));
        return sides;
    }

    public double getCircleRadius(List<Point> coordinates) {
        //https://www.fxyz.ru/%D1%84%D0%BE%D1%80%D0%BC%D1%83%D0%BB%D1%8B_%D0%BF%D0%BE_%D0%B3%D0%B5%D0%BE%D0%BC%D0%B5%D1%82%D1%80%D0%B8%D0%B8/%D0%BF%D0%BB%D0%BE%D1%81%D0%BA%D0%B8%D0%B5_%D1%84%D0%B8%D0%B3%D1%83%D1%80%D1%8B/%D0%B2%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5_%D0%B8_%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%BD%D1%8B%D0%B5_%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA%D0%B8/%D1%80%D0%B0%D0%B4%D0%B8%D1%83%D1%81_%D0%BE%D0%BF%D0%B8%D1%81%D0%B0%D0%BD%D0%BD%D0%BE%D0%B9_%D0%BE%D0%BA%D1%80%D1%83%D0%B6%D0%BD%D0%BE%D1%81%D1%82%D0%B8/%D1%82%D1%80%D0%B5%D1%83%D0%B3%D0%BE%D0%BB%D1%8C%D0%BD%D0%B8%D0%BA%D0%B0/
        List<Double> sidesOfTriangle = this.getSidesOfTriangle(coordinates);
        Double a = sidesOfTriangle.get(0);
        Double b = sidesOfTriangle.get(1);
        Double c = sidesOfTriangle.get(2);
        double p = (a + b + c) / 2;
        return (a * b * c) / (4 * Math.sqrt(p * (p - a) * (p - b) * (p - c)));
    }

    @Override
    public int getMinimalPointsInside() {
        return 3;
    }

    @Override
    public void setTextToLabel(String labelText) {
        if (this.label != null)
            this.label.setText(labelText);
    }
}
