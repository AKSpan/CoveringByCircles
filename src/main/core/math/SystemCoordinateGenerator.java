package main.core.math;

import main.core.objects.Circle;
import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.util.Random;

/**
 * Класс для создания системы координат с точками на ней.
 * Основывается на параметрах:
 * - количество точек в системе координат
 * - Максимальные координаты х и у
 */
public class SystemCoordinateGenerator {
    private int maxX, maxY, pointCount;
    private CoordinateSystem coordinateSystem;
    private int marginY;
    private int marginX;

    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
    }

    public SystemCoordinateGenerator(int maxX, int maxY, int pointCount, int marginX, int marginY) {
        this.maxX = maxX;
        this.maxY = maxX;
        this.marginX = marginX;
        this.marginY = marginY;
        this.pointCount = pointCount;

        System.out.printf("MaxX %s; MaxY %s\n", maxX, maxY);
        System.out.printf("marginX %s; marginY %s\n", marginX, marginY);

    }


    /**
     * Метод для создания системы координат
     *
     * @return
     */
    public CoordinateSystem generate(GuiBuilderV2 gui) {
        //создаем объект типа СИСТЕМА КООРДИНАТ
        this.coordinateSystem = new CoordinateSystem(this.maxX, this.maxY);
        //добавляем точки

        for (int i = 0; i < pointCount; i++) {
            if (!gui.getLogicWrapper().isStopAction()) {
                //объект для генерации случайных чисел
                Random rnd = new Random();
                //Создаем точку с координатами в диапозоне координат 0 < X <  MAX_X; 0 < Y < MAX_Y
                Point p = new Point(rnd.nextInt((maxX - 29) + 1) + this.marginX, rnd.nextInt((maxY - 88) + 1) + this.marginY);
                gui.getUiComponentsHolder().setLabelText(String.format("Шаг %s/%s", i + 1, pointCount));
                //добавляем в массив точек системы координат нашу точку
                coordinateSystem.addPoint(p);
                coordinateSystem.addCircle(new Circle(p, 10));
            }
        }
        //после генерации возвращаем созданый и заполненный точками объект
        return coordinateSystem;
    }

    /////////////////////////////////////////////
//Методы для получения соответтвующих данных
/////////////////////////////////////////////
    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getPointCount() {
        return pointCount;
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount;
    }

}
