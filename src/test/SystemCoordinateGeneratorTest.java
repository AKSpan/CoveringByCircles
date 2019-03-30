package test;


import main.gui.GuiBuilder;
import org.junit.Test;

import main.core.objects.CoordinateSystem;
import main.core.SystemCoordinateGenerator;

import javax.swing.*;

public class SystemCoordinateGeneratorTest {

    @Test
    public void generate() {
        SystemCoordinateGenerator systemCoordinateGenerator = new SystemCoordinateGenerator(100, 100, 1000);
        CoordinateSystem generate = systemCoordinateGenerator.generate(new GuiBuilder(1,1,11,""));
        System.out.println(generate.getPoints().size());

    }
}