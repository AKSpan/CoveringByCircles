package test;


import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;
import org.junit.Test;

import main.core.objects.CoordinateSystem;
import main.core.SystemCoordinateGenerator;

import javax.swing.*;

public class SystemCoordinateGeneratorTest {

    @Test
    public void generate() {
        SystemCoordinateGenerator systemCoordinateGenerator = new SystemCoordinateGenerator(100, 100, 1000);
        CoordinateSystem generate = systemCoordinateGenerator.generate(new GuiBuilderV2().build());
        System.out.println(generate.getPoints().size());

    }
}