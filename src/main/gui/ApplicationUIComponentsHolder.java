package main.gui;

import main.gui.custom.panels.DrawPanel;
import main.gui.custom.panels.RightPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Класс, хранящий компоненты, отрисовываемые на форме + доступ к ним
 */
public class ApplicationUIComponentsHolder {
    private static final int MARGIN_X = 50;
    private static final int MARGIN_Y = 50;
    private static final int SIZE_X = 900;
    private static final int SIZE_Y = 600;
    private static final int SYSTEM_COORDINATE_SIZE_X = 505;
    private static final int SYSTEM_COORDINATE_SIZE_Y = 520;
    private static final Dimension MAIN_FRAME_SIZE = new Dimension(SIZE_X, SIZE_Y);
    private JPanel footer;
    private JLabel operationLabel;
    private JPanel leftPanel;
    private RightPanel rightPanel;
    private JMenuBar menu;
    private DrawPanel drawPanel;

    public JPanel getFooter() {
        return footer;
    }

    public ApplicationUIComponentsHolder setFooter(JPanel footer) {
        this.footer = footer;
        return this;
    }

    public JLabel getOperationLabel() {
        return operationLabel;
    }

    public ApplicationUIComponentsHolder setOperationLabel(JLabel operationLabel) {
        this.operationLabel = operationLabel;
        return this;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public ApplicationUIComponentsHolder setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
        return this;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    public ApplicationUIComponentsHolder setRightPanel(RightPanel rightPanel) {
        this.rightPanel = rightPanel;
        return this;
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    public ApplicationUIComponentsHolder setDrawPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        return this;
    }

    public JMenuBar getMenu() {
        return menu;
    }

    public ApplicationUIComponentsHolder setMenu(JMenuBar menu) {
        this.menu = menu;
        return this;
    }

    public void setLabelText(String text) {
        this.operationLabel.setText(text);
    }

    public void setEnabledForButtons(boolean b) {
        this.leftPanel.setEnabled(b);
    }

    public int getMarginX() {
        return MARGIN_X;
    }

    public int getMarginY() {
        return MARGIN_Y;
    }

    public int getSizeX() {
        return SIZE_X;
    }

    public int getSizeY() {
        return SIZE_Y;
    }

    public int getSystemCoordinateSizeX() {
        return SYSTEM_COORDINATE_SIZE_X;
    }

    public int getSystemCoordinateSizeY() {
        return SYSTEM_COORDINATE_SIZE_Y;
    }

    public Dimension getMainFrameSize() {
        return MAIN_FRAME_SIZE;
    }
}
