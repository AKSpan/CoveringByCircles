package main.core.math;

import main.core.objects.Circle;
import main.core.objects.Point;
import main.core.objects.SquareBlock;

import javax.swing.*;

public class GetCircleOutSquare {
    private SquareBlockFactory squareBlockFactory;
    private long minPoints;
private SquareBlock squareBlock;
    public GetCircleOutSquare(SquareBlockFactory squareBlockFactory, long minPoints) {
        this.squareBlockFactory = squareBlockFactory;
        this.minPoints = minPoints;
    }

    public Circle getCircle() {
        this.squareBlock= this.squareBlockFactory.getBlockWithMaxPointsInside(this.minPoints);
        if (this.squareBlock == null) {
            JOptionPane.showConfirmDialog(null, "Отсутствует окружность, покрывающая минимум " + minPoints + " точку(ек)", "Ошибка", JOptionPane.DEFAULT_OPTION);

        }
        int startX = this.squareBlock.getStartX();
        int startY = this.squareBlock.getStartY();

        int stepX = this.squareBlock.getStepX();
        int stepY = this.squareBlock.getStepY();
        double radius;
        if (stepX == stepY)
            radius = (stepX / 2d) * Math.sqrt(2);
        else
            radius = Math.sqrt(stepX * stepX + stepY * stepY) / 2d;

        return new Circle(new Point((startX + startX + stepX) / 2d, (startY + startY + stepY) / 2d), radius);
    }

    public SquareBlock getSquareBlock() {
        return squareBlock;
    }
}
