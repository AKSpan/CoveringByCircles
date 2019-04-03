package main.core.math;

import main.core.objects.Circle;
import main.core.objects.Point;
import main.core.objects.SquareBlock;

public class GetCircleOutSquare {
    private SquareBlockFactory squareBlockFactory;
    private long minPoints;

    public GetCircleOutSquare(SquareBlockFactory squareBlockFactory, long minPoints) {
        this.squareBlockFactory = squareBlockFactory;
        this.minPoints = minPoints;
    }

    public Circle getCircle() {
        SquareBlock blockWithMaxPointsInside = this.squareBlockFactory.getBlockWithMaxPointsInside(this.minPoints);
        if (blockWithMaxPointsInside == null)
            throw new NullPointerException("Отсутствует окружность, покрывающая минимум " + minPoints + " точку(ек)");
        int startX = blockWithMaxPointsInside.getStartX();
        int startY = blockWithMaxPointsInside.getStartY();

        int stepX = blockWithMaxPointsInside.getStepX();
        int stepY = blockWithMaxPointsInside.getStepY();
        double radius = 0;
        if (stepX == stepY)
            radius = (stepX / 2d) * Math.sqrt(2);
        else
            radius = Math.sqrt(stepX * stepX + stepY * stepY) / 2d;

        return new Circle(new Point((startX + startX + stepX) / 2d, (startY + startY + stepY) / 2d), radius);
    }
}
