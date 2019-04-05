package main.core.objects;

import java.util.HashSet;
import java.util.Set;

/**
 * Система координат
 * Содержит массив точек на ней и массив окружностей, которые были построены.
 * Дополнительно указывается максимальное значение координат X и Y
 */
public class CoordinateSystem {
    private int maxX, maxY;
    private double minX, minY;
    private Point minPoint;
    //HashSet - так называемый список не повторяемых объектов - удобный для работы массив
    private HashSet<Point> points;
    private HashSet<Circle> circles;
    private Circle circle;

    public CoordinateSystem(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        //при создании объекта типа Система координат
        //задаем максимальные значения координат
        //и создаем пустые массив для будущей работы
        this.points = new HashSet<>();
        this.circles = new HashSet<>();
    }

    public void clear() {
        this.points = new HashSet<>();
        this.circles = new HashSet<>();
    }

    /**
     * Метод для добавления точки в систему координат.
     */
    public void addPoint(Point p) {
        if (this.minPoint == null) {
            minX = maxX+1;minY=maxY+1;
            this.minPoint = new Point(minX, minY);
        }
        if(p.getX() <= this.minPoint.getX()) {
            this.minX = p.getX();
            this.minPoint.setX(p.getX());
        }
        if(p.getY()<=this.minPoint.getY())
        {
            this.minY = p.getY();
            this.minPoint.setY(p.getY());

        }
        this.points.add(p);
    }

    /**
     * Метод для добавления окружности в систему координат.
     *
     * @param p окружность
     */
    public void addCircle(Circle p) {
        this.circles.add(p);
    }
/////////////////////////////////////////////
//Методы для получения соответствующих данных
/////////////////////////////////////////////

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public HashSet<Point> getPoints() {
        return points;
    }

    public HashSet<Circle> getCircles() {
        return circles;
    }

    public void removePoints() {
        this.points = new HashSet<>();
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    private String pointToString() {
        StringBuilder sb = new StringBuilder("POINTS:\n");
        for (Point point : this.points) {
            sb.append(point.toString()).append("\n");
        }
        return sb.toString();
    }

    private String circleDataToString() {
        return circle != null ? "CIRCLE:\n" + circle.getCenter() + "," + circle.getRadius() : "";
    }

    public String fullDataString() {

        return pointToString() + circleDataToString();
    }

    public void addPoints(Set<Point> points) {
        this.points = new HashSet<>(points);
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }
}
