package main.core.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Объект с полной информацией об результате вычислений
 */
public class ResultInfo {
    /**
     * Результирующая окружность
     */
    private Circle circle;
    /**
     * Список точек внутри окружности
     */
    private List<Point> points;

    private String algorithmNameLabel;

    public ResultInfo() {
        this.algorithmNameLabel = "UNNAMED";
        this.points = new ArrayList<>();
        this.circle = new Circle(new Point(0, 0), 0);
    }

    public ResultInfo(Circle circle, List<Point> points, String algorithmNameLabel) {
        this.circle = circle;
        this.points = points;
        this.algorithmNameLabel = algorithmNameLabel;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getAlgorithmNameLabel() {
        return algorithmNameLabel;
    }

    public void setAlgorithmNameLabel(String algorithmNameLabel) {
        this.algorithmNameLabel = algorithmNameLabel;
    }
}
