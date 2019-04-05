package main.core.math;

import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.core.objects.SquareBlock;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SquareBlockFactory {
    private CoordinateSystem coordinateSystem;
    private Set<Point> points;
    private int blockDividerCounter;
    private List<SquareBlock> squareBlocks;
    private double sizeX;
    private double sizeY;
    private double initX;
    private double initY;
    private Graphics2D graphics2D;

    public SquareBlockFactory(CoordinateSystem coordinateSystem, int blockDividerCounter, Graphics2D graphics2D) {
        this(coordinateSystem.getMaxX(), coordinateSystem.getMaxY(), coordinateSystem.getMinX(), coordinateSystem.getMinY(), coordinateSystem.getPoints(), blockDividerCounter, graphics2D);
    }


    public SquareBlockFactory(double sizeX, double sizeY, double initX, double initY, Set<Point> points, int blockDividerCounter, Graphics2D graphics2D) {
        this.blockDividerCounter = blockDividerCounter;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.initX = initX;
        this.initY = initY;
        this.points = points;
        this.graphics2D = graphics2D;

    }

    private List<SquareBlock> getBlocks() {
        this.squareBlocks = new ArrayList<>(4);

        double step = sizeX > sizeY ? sizeX / 2 : sizeY / 2;

        double stepX = step;// (sizeX + initX) / 2;
        double stepY = step;//(sizeY + initY) / 2;
        this.squareBlocks.add(new SquareBlock(initX, initY, stepX, stepY));
        this.squareBlocks.add(new SquareBlock(initX + stepX, initY, stepX, stepY));
        this.squareBlocks.add(new SquareBlock(initX, initY + stepY, stepX, stepY));
        this.squareBlocks.add(new SquareBlock(initX + stepX, initY + stepY, stepX, stepY));
        //  this.drawSquareBlockOnDrawPanel();
        return this.squareBlocks;
    }

    private void drawSquareBlockOnDrawPanel() {
        if (this.graphics2D != null) {
            graphics2D.setBackground(Color.BLUE);
            for (SquareBlock squareBlock : this.squareBlocks) {
                graphics2D.drawString("text", (int) squareBlock.getStepX(), (int) squareBlock.getStepY());

            }

        }
    }

    public SquareBlock getBlockWithMaxPointsInside(long minPoints) {
        this.squareBlocks = this.getBlocks();

        SquareBlock resultBlock = null;
        for (int i = 0; i < blockDividerCounter; i++) {
            int maxPoints = 0;
            SquareBlock squareBlock = null;
            List<SquareBlock> blocks = this.squareBlocks;
            for (SquareBlock block : blocks) {
                int size = block.calculatePointsInside(points).getPointsInside().size();
                if (maxPoints < size && size >= minPoints) {
                    maxPoints = block.getPointsInside().size();
                    squareBlock = block;
                }
            }
            if (squareBlock != null) {
                resultBlock = squareBlock;
                this.squareBlocks = new SquareBlockFactory(squareBlock.getStepX(),
                        squareBlock.getStepY(),
                        squareBlock.getStartX(),
                        squareBlock.getStartY(),
                        points,
                        this.blockDividerCounter, this.graphics2D).getBlocks();
            }
        }


        return resultBlock;
    }
}
