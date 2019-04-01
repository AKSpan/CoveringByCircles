package main.gui.custom.panels;

import main.core.IAlgorithm;
import main.core.objects.Circle;
import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.core.objects.ResultInfo;
import main.gui.GuiBuilderV2;
import main.gui.listener.AddPointToSystemCoordinateMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Set;

public class DrawPanel extends JPanel {
    private CoordinateSystem coordinateSystem;
    private JTextField jTextField;
    private GuiBuilderV2 guiBuilderV2;

    public DrawPanel(GuiBuilderV2 guiBuilderV2) {
        this.guiBuilderV2 = guiBuilderV2;
        this.coordinateSystem = guiBuilderV2.getLogicWrapper().getCoordinateSystem();
        this.setBackground(Color.WHITE);
        this.addMouseListener(new AddPointToSystemCoordinateMouseListener(this.guiBuilderV2));
    }

    @Override
    protected void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);
        g2d.setColor(Color.red);

        g2d.drawLine(10, 10, 10, this.getHeight() - 10);
        g2d.drawLine(10, this.getHeight() - 10, this.getWidth() - 10, this.getHeight() - 10);
    }

    public void addPoints(CoordinateSystem coordinateSystem, Circle c) {
        this.coordinateSystem = coordinateSystem;
        Set<Point> pointList = this.coordinateSystem.getPoints();
        addPoints(pointList, c);
    }

    public void addPoints(Set<Point> pointList, Circle c) {
        System.out.println("addPoints");
        Graphics2D graphics = (Graphics2D) super.getGraphics();
        this.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        for (Point point : pointList) {
            graphics.fillRect((int) point.getX(), (int) point.getY(), 2, 2);
        }
        if (c != null) {
            graphics.setColor(Color.GREEN);
            graphics.draw(new Ellipse2D.Double((c.getCenter().getX() - c.getRadius() + 1),
                    (c.getCenter().getY() - c.getRadius() + 1),
                    c.getRadius() * 2,
                    c.getRadius() * 2));


        }
        changeTextFieldText(String.valueOf(pointList.size()));
    }


    public void calculateDensityAndDrawCircle(GuiBuilderV2 guiBuilder) {
        IAlgorithm algorithm = guiBuilder.getLogicWrapper().getAlgorithm();
        ResultInfo resultInfo = algorithm.calculateDensityAndDrawCircle(guiBuilder);
        //Стираем предыдущую окружность
        this.addPoints(this.coordinateSystem.getPoints(), null);
        //Рисуем новую
        Graphics2D graphics = (Graphics2D) super.getGraphics();
        graphics.setColor(Color.GREEN);
        graphics.draw(new Ellipse2D.Double((resultInfo.getCircle().getCenter().getX() - resultInfo.getCircle().getRadius() + 1),
                (resultInfo.getCircle().getCenter().getY() - resultInfo.getCircle().getRadius() + 1),
                resultInfo.getCircle().getRadius() * 2,
                resultInfo.getCircle().getRadius() * 2));


    }


    public void drawGridPanel() {
        System.out.println(this.getSize());
        Graphics graphics = this.getGraphics();
        graphics.setColor(Color.GREEN);
        for (int i = this.getWidth(); i > 0; i -= 50) {
            graphics.drawLine(i, 0, i, this.getHeight());

        }

    }

    public void addTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    public void changeTextFieldText(String s) {
        if (jTextField != null)
            this.jTextField.setText(s);
    }

    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
    }
}