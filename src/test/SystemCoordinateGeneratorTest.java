package test;


import main.gui.GuiBuilderV2;
import org.junit.Test;

import main.core.objects.CoordinateSystem;
import main.core.math.SystemCoordinateGenerator;

public class SystemCoordinateGeneratorTest {

    @Test
    public void generate() {
        SystemCoordinateGenerator systemCoordinateGenerator = new SystemCoordinateGenerator(100, 100, 1000,10,10);
        CoordinateSystem generate = systemCoordinateGenerator.generate(new GuiBuilderV2().build());
        System.out.println(generate.getPoints().size());

    }
}