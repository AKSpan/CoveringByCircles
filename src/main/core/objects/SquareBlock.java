package main.core.objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс описывающий блок в системе координат для поиска точек
 */
public class SquareBlock {
    private double startX, startY, stepX, stepY;
    private Set<Point> pointInside;

    public SquareBlock(double startX, double startY, double stepX, double stepY) {
        this.pointInside = new HashSet<>();
        this.startX = startX;
        this.startY = startY;
        this.stepX = stepX;
        this.stepY = stepY;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getStepX() {
        return stepX;
    }

    public double getStepY() {
        return stepY;
    }

    public void addPoint(Point p) {
        this.pointInside.add(p);
    }

    public Set<Point> getPointsInside() {
        return pointInside;
    }

    public SquareBlock calculatePointsInside(Set<Point> points) {
        for (Point point : points) {
            if (point.getX() >= this.startX && point.getX() < this.startX + this.stepX
                    &&
                    point.getY() >= this.startY && point.getY() < this.startY + this.stepY) {
                this.pointInside.add(point);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return "SquareBlock{" +
                "startX=" + startX +
                ", startY=" + startY +
                ", stepX=" + stepX +
                ", stepY=" + stepY +
                ", pointsSize=" + pointInside.size() +
                '}';
    }
}
