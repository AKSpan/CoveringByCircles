package main.core.math;

import main.core.objects.Circle;
import main.core.objects.Point;
import main.core.objects.SquareBlock;

import javax.swing.*;

/**
 * Построение окружность вокруг квадрата
 */
public class GetCircleOutSquare {
    private SquareBlockFactory squareBlockFactory;
    private long minPoints;
    private SquareBlock squareBlock;

    public GetCircleOutSquare(SquareBlockFactory squareBlockFactory, long minPoints) {
        this.squareBlockFactory = squareBlockFactory;
        this.minPoints = minPoints;
    }

    public Circle getCircle() {
        this.squareBlock = this.squareBlockFactory.getBlockWithMaxPointsInside(this.minPoints);
        if (this.squareBlock == null) {
            JOptionPane.showConfirmDialog(null, "Отсутствует окружность, покрывающая минимум " + minPoints + " точку(ек)", "Ошибка", JOptionPane.DEFAULT_OPTION);
            return new Circle(new Point(0, 0), 0);
        } else {
            double startX = this.squareBlock.getStartX();
            double startY = this.squareBlock.getStartY();

            double stepX = this.squareBlock.getStepX();
            double stepY = this.squareBlock.getStepY();
            double radius;
            if (stepX == stepY)
                radius = (stepX / 2d) * Math.sqrt(2);
            else
                radius = Math.sqrt(stepX * stepX + stepY * stepY) / 2d;

            return new Circle(new Point((startX + startX + stepX) / 2d, (startY + startY + stepY) / 2d), radius);
        }
    }

    public SquareBlock getSquareBlock() {
        return squareBlock;
    }
}
