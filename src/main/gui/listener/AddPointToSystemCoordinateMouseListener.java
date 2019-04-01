package main.gui.listener;

import main.core.objects.Point;
import main.gui.GuiBuilderV2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddPointToSystemCoordinateMouseListener implements MouseListener {
    private GuiBuilderV2 guiBuilderV2;

    public AddPointToSystemCoordinateMouseListener(GuiBuilderV2 guiBuilderV2) {
        this.guiBuilderV2 = guiBuilderV2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("AddPointToSystemCoordinateMouseListener");

        int x = e.getX();
        int y = e.getY();
        if (x <= this.guiBuilderV2.getLogicWrapper().getCoordinateSystem().getMaxX() && y <= this.guiBuilderV2.getLogicWrapper().getCoordinateSystem().getMaxY()) {
            this.guiBuilderV2.getLogicWrapper().getCoordinateSystem().addPoint(new Point(x, y));
            this.guiBuilderV2.getUiComponentsHolder().getDrawPanel().addPoints(this.guiBuilderV2.getLogicWrapper().getCoordinateSystem(), null);
            this.guiBuilderV2.getUiComponentsHolder().setLabelText(String.format("Точек %s", this.guiBuilderV2.getLogicWrapper().getCoordinateSystem().getPoints().size()));
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
}
