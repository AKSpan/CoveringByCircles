package main.core.objects;

import java.util.Objects;

/**
 * Класс, описывающий точку
 * X, Y - координаты
 */
public class Point {
    private double x, y;

    /**
     * Конструктор, для создания объекта ТОЧКА с заданными координатами
     *
     * @param x координата х
     * @param y координата у
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getPowX() {
        return x * x;
    }

    public double getPowY() {
        return y * y;
    }

    /**
     * Метод для получения координаты Х объекта ТОЧКА
     *
     * @return
     */
    public double getX() {
        return x;
    }

    /**
     * Метод для получения координаты Y объекта ТОЧКА
     *
     * @return
     */
    public double getY() {
        return y;
    }
    //Для корректной работы при добавлении точки в соответствующий список, нужно описать работу спец. методов

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x+","+y;
    }
}
