package main.core.objects;

import java.util.Objects;

/**
 * Класс для описания объектов типа ОКРУЖНОСТЬ
 * Описывается точкой центра и радиусом
 */
public class Circle {
    private Point center;
    private double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isPointInCircle(Point p) {
        double sqrt = Math.sqrt(Math.pow((p.getX() - this.center.getX()), 2) + Math.pow((p.getY() - this.center.getY()), 2));
        return sqrt < radius;
    }

    //Для корректной работы при добавлении окружностей в соответствующий список, нужно описать работу спец. методов
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius &&
                Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
