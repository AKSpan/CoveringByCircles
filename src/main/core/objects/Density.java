package main.core.objects;

import java.util.List;

/**
 * Класс описывающий ПЛОТНОСТЬ точек в окружности
 */
public class Density {
    private List<Point> points;
    private Circle circle;

    public Density(List<Point> points, Circle circle) {
        this.points = points;
        this.circle = circle;
    }

    /**
     * Вычисление плотности точек
     *
     * @return кол-во точек в окружности / площадь окр-ти
     */
    public double getDensity() {
        return this.points.size() / this.getCircleArea();
    }

    /**
     * Вычисление площади круга
     *
     * @return PI * R^2
     */
    private double getCircleArea() {
        return Math.PI * Math.pow(this.circle.getRadius(), 2);
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    @Override
    public String toString() {
        return "Density{" +
                "points=" + points +
                ", circle=" + circle +
                '}';
    }
}
