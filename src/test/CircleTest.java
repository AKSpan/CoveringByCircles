package test;

import main.core.objects.Circle;
import main.core.objects.Point;
import org.junit.Assert;
import org.junit.Test;

public class CircleTest {
    private Circle circle = new Circle(new Point(5, 5), 6);

    @Test
    public void isPointInCircle() {
        Assert.assertTrue(circle.isPointInCircle(new Point(5,4)));
        Assert.assertTrue(circle.isPointInCircle(new Point(5,5)));
        Assert.assertTrue(circle.isPointInCircle(new Point(1,1)));
        Assert.assertFalse(circle.isPointInCircle(new Point(0,-5)));
    }
}