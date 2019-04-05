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
        SystemCoordinateGenerator systemCoordinateGenerator = new SystemCoordinateGenerator(100, 100, 50,10,10);
        CoordinateSystem generate = systemCoordinateGenerator.generate(new GuiBuilderV2().build());

        SquareBlockFactory squareBlockFactory = new SquareBlockFactory(generate, 4,null);
        System.out.println(squareBlockFactory.getBlockWithMaxPointsInside(35));
        GetCircleOutSquare getCircleOutSquare = new GetCircleOutSquare(squareBlockFactory,35);
        System.out.println(getCircleOutSquare.getCircle());
    }
}