package main.core.math;

import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.core.objects.SquareBlock;
import main.gui.GuiBuilderV2;

import java.awt.*;
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
    private GuiBuilderV2 guiBuilderV2;

    public SquareBlockFactory(CoordinateSystem coordinateSystem, int blockDividerCounter, Graphics2D graphics2D, GuiBuilderV2 guiBuilderV2) {
        this(coordinateSystem, coordinateSystem.getMaxX(), coordinateSystem.getMaxY(), coordinateSystem.getMinX(), coordinateSystem.getMinY(), coordinateSystem.getPoints(), blockDividerCounter, graphics2D, guiBuilderV2);

    }


    public SquareBlockFactory(CoordinateSystem coordinateSystem, double sizeX, double sizeY, double initX, double initY, Set<Point> points, int blockDividerCounter, Graphics2D graphics2D, GuiBuilderV2 guiBuilderV2) {
        this.blockDividerCounter = blockDividerCounter;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.initX = initX;
        this.initY = initY;
        this.points = points;
        this.graphics2D = graphics2D;
        this.coordinateSystem = coordinateSystem;
        this.guiBuilderV2 = guiBuilderV2;
    }

    private List<SquareBlock> getBlocks(int stepCount) {
        this.squareBlocks = new ArrayList<>(4);

        double step = coordinateSystem.getMaxBoundsX() > coordinateSystem.getMaxBoundsY() ? coordinateSystem.getMaxBoundsX() / (2 + stepCount) : coordinateSystem.getMaxBoundsY() / (2 + stepCount);
//        double step = sizeX > sizeY ? sizeX  / 2 : sizeY / 2;

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
        this.squareBlocks = this.getBlocks(0);

        SquareBlock resultBlock = null;
        for (int i = 0; i < blockDividerCounter; i++) {
            if (this.guiBuilderV2.getLogicWrapper().isStopAction())
                break;
            double minSquare = Double.MAX_VALUE;
            SquareBlock squareBlock = null;
            List<SquareBlock> blocks = this.squareBlocks;
            for (SquareBlock block : blocks) {
                int pointsInside = block.calculatePointsInside(points).getPointsInside().size();
                double currentBlockSquare = (block.getStepX() * block.getStepY()) / pointsInside;
                if (currentBlockSquare < minSquare && pointsInside >= minPoints) {
                    minSquare = currentBlockSquare;
                    squareBlock = block;
                }
            }
            //todo - проблема в размерах при разиение на блоки - бьются сверху вниз и нижние блоки захватывают поле вне системы координат
            if (squareBlock != null) {
                resultBlock = squareBlock;
                this.squareBlocks = new SquareBlockFactory(
                        this.coordinateSystem,
                        squareBlock.getStepX(),
                        squareBlock.getStepY(),
                        squareBlock.getStartX(),
                        squareBlock.getStartY(),
                        points,
                        this.blockDividerCounter, this.graphics2D, this.guiBuilderV2).getBlocks(i);
            }
        }


        return resultBlock;
    }
}
