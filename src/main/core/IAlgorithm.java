package main.core;

import main.core.objects.*;
import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.util.*;

/**
 * Интерфейс для алгоритмов
 */
public interface IAlgorithm {

    String getAlgorithmName();

    default boolean isPointInCircle(Circle circle, Point p) {
        return Math.sqrt(Math.pow((p.getX() - circle.getCenter().getX()), 2) + Math.pow((p.getY() - circle.getCenter().getY()), 2)) <= circle.getRadius();
    }

    default double getSide(Point a, Point b) {
        return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
    }

    default Circle getCircle(List<Point> points, int minPoints) {
        if (points.size() != minPoints)
            throw new IllegalArgumentException("Неверное число точек для вычисления центра окружности. Требутеся " + minPoints + ", но передано " + points.size());
        double circleX, circleY, radius;
        Point pA = points.get(0);
        Point pB = points.get(1);
        //https://0oq.ru/reshebnik-onlajn/ru.onlinemschool.com/math/library/analytic_geometry/points_center/default.htm
        circleX = (pA.getX() + pB.getX()) / 2;
        circleY = (pA.getY() + pB.getY()) / 2;
        radius = getCircleRadius(points);

        return new Circle(new Point(circleX, circleY), radius);
    }

    default Density getMaxDensity(Map<Circle, List<Point>> circlesWithPointsInside, long minPointsInCircle) {
        double maxDensityValue = 0d;
        Map<Double, Density> densityMap = new HashMap<>(circlesWithPointsInside.size());
        for (Map.Entry<Circle, List<Point>> circleListEntry : circlesWithPointsInside.entrySet()) {
            Density d = new Density(circleListEntry.getValue(), circleListEntry.getKey());
            double densityValue = d.getDensity();
            densityMap.put(densityValue, d);
            if (circleListEntry.getValue().size() >= minPointsInCircle && densityValue > maxDensityValue)
                maxDensityValue = densityValue;
        }
        return densityMap.get(maxDensityValue);

    }

    default Map<Circle, List<Point>> getCirclesWithPointsInside(List<Circle> circlesByPoints, Set<Point> points,long minPoints) {
        Point[] objects = points.toArray(new Point[]{});
        Map<Circle, List<Point>> circlesWithPointsInside = new HashMap<>();
        for (Circle circlesByPoint : circlesByPoints) {
            List<Point> pointsInsideCircle = new ArrayList<>();
            for (Point point : objects) {
                if (this.isPointInCircle(circlesByPoint, point))
                    pointsInsideCircle.add(point);
            }
            if (pointsInsideCircle.size() >= minPoints)
                circlesWithPointsInside.put(circlesByPoint, pointsInsideCircle);
        }
        return circlesWithPointsInside;

    }

    double getCircleRadius(List<Point> coordinates);

    ResultInfo calculateDensityAndDrawCircle(GuiBuilderV2 guiBuilder);
}
