package main.gui.custom.panels;

import javax.swing.*;

/**
 * Правая панель формы с тектовым полем
 */
public class RightPanel {
    private JPanel rightPanel;
    private JTextArea textArea;

    public RightPanel(JPanel rightPanel, JTextArea textArea) {
        this.rightPanel = rightPanel;
        this.textArea = textArea;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
