package main.gui.custom.panels;

import main.core.IAlgorithm;
import main.core.objects.Circle;
import main.core.objects.CoordinateSystem;
import main.core.objects.Point;
import main.core.objects.ResultInfo;
import main.gui.GuiBuilder;
import main.gui.GuiBuilderV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Collections;
import java.util.Set;

public class DrawPanel extends JPanel {
    private CoordinateSystem coordinateSystem;
    private JTextField jTextField;

    public DrawPanel(CoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        this.setBackground(Color.LIGHT_GRAY);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("CLICK");
                int x = e.getX();
                int y = e.getY();
                if (x <= DrawPanel.this.coordinateSystem.getMaxX() && y <= DrawPanel.this.coordinateSystem.getMaxY()) {
                    //Graphics graphics = DrawPanel.this.getGraphics();
                    DrawPanel.this.coordinateSystem.addPoint(new Point(x, y));
                    DrawPanel.this.addPoints(DrawPanel.this.coordinateSystem, null);

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
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
        IAlgorithm algorithm = guiBuilder.getAlgorithm();
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
}