package test;

import main.core.math.GetCircleOutSquare;
import main.core.math.SquareBlockFactory;
import main.core.math.SystemCoordinateGenerator;
import main.core.objects.CoordinateSystem;
import main.core.objects.SquareBlock;
import main.gui.GuiBuilderV2;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareBlockFactoryTest {

    @Test
    public void getBlocks() {
        SystemCoordinateGenerator systemCoordinateGenerator = new SystemCoordinateGenerator(400, 400, 100);
        CoordinateSystem generate = systemCoordinateGenerator.generate(new GuiBuilderV2().build());
        SquareBlockFactory squareBlockFactory = new SquareBlockFactory(400, 400, 0, 0, generate.getPoints(), 4);
        System.out.println(squareBlockFactory.getBlockWithMaxPointsInside(35));
        GetCircleOutSquare getCircleOutSquare = new GetCircleOutSquare(squareBlockFactory,35);
        System.out.println(getCircleOutSquare.getCircle());
    }
}