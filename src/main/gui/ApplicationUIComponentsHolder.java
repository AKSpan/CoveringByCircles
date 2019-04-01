package main.gui;

import main.gui.custom.panels.DrawPanel;

import javax.swing.*;

/**
 * Класс, хранящий компоненты, отрисовываемые на форме + доступ к ним
 */
public class ApplicationUIComponentsHolder {
    private JPanel footer;
    private JLabel operationLabel;
    private JPanel leftPanel;
    private JPanel rightPanel;
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

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public ApplicationUIComponentsHolder setRightPanel(JPanel rightPanel) {
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
}
