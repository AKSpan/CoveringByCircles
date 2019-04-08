package main.gui.listener;

import main.core.enums.LoggerTextTemplates;
import main.core.objects.Point;
import main.gui.GuiBuilderV2;
import main.gui.UILogger;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddPointToSystemCoordinateMouseListener extends AbstractLogger implements MouseListener {
    private GuiBuilderV2 guiBuilderV2;

    public AddPointToSystemCoordinateMouseListener(GuiBuilderV2 guiBuilderV2) {
        super(new UILogger(guiBuilderV2.getUiComponentsHolder()));
        this.guiBuilderV2 = guiBuilderV2;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        System.out.printf("mouseClicked %s;%s\n",x,y);
        if ((x >= this.guiBuilderV2.getUiComponentsHolder().getMarginX() && x <= this.guiBuilderV2.getLogicWrapper().getCoordinateSystem().getMaxX()) &&
                (y >= this.guiBuilderV2.getUiComponentsHolder().getMarginY() && y <= this.guiBuilderV2.getLogicWrapper().getCoordinateSystem().getMaxY())) {
            Point point = new Point(x, y);
            this.log(LoggerTextTemplates.addPointEvent(point));
            this.guiBuilderV2.getLogicWrapper().getCoordinateSystem().addPoint(point);
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
