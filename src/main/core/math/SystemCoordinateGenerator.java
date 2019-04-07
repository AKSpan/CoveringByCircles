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
    private int maxBoundX;
    private int maxBoundY;

    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
    }

    public SystemCoordinateGenerator(int maxX, int maxY, int pointCount, int marginX, int marginY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.marginX = marginX;
        this.marginY = marginY;
        this.pointCount = pointCount;
//        this.maxBoundX = (maxX - 29) + 1;
//        this.maxBoundY = (maxY - 88) + 1;

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
        this.maxBoundX = gui.getUiComponentsHolder().getSystemCoordinateSizeX();
        this.maxBoundY = gui.getUiComponentsHolder().getSystemCoordinateSizeY()-gui.getUiComponentsHolder().getMarginY();
        this.coordinateSystem.setBoundsX(this.maxBoundX);
        this.coordinateSystem.setBoundsY(this.maxBoundY);
        //добавляем точки

        for (int i = 0; i < pointCount; i++) {
            if (!gui.getLogicWrapper().isStopAction()) {
                //объект для генерации случайных чисел
                Random rnd = new Random();
                //Создаем точку с координатами в диапозоне координат MARGIN_X < X <  MAX_X-MARGIN_X; MARGIN_Y < Y < MAX_Y - MARGIN_Y
                Point p = new Point(rnd.nextInt(this.maxBoundX-this.marginX) + this.marginX, rnd.nextInt(this.maxBoundY-this.marginY) + this.marginY);
                gui.getUiComponentsHolder().setLabelText(String.format("Шаг %s/%s", i + 1, pointCount));
                //добавляем в массив точек системы координат нашу точку
                coordinateSystem.addPoint(p);
                coordinateSystem.addCircle(new Circle(p, 10));
            }
            else break;
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

    /**
     * Максимальное значение точки на графической системе координат
     * Отличается от MaxX[Y] в виду отрисовки графики
     * @return
     */
    private int getMaxBoundX() {
        return maxBoundX;
    }
    /**
     * Максимальное значение точки на графической системе координат
     * Отличается от MaxX[Y] в виду отрисовки графики
     * @return
     */
    private int getMaxBoundY() {
        return maxBoundY;
    }
}
