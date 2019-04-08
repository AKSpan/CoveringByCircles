package main.gui.custom.panels;

import javax.swing.*;
import java.awt.*;

public class FooterPanel {
    private final static int SIZE_20 = 20;
    private final static int SIZE_600 = 600;
    private JLabel operationInfoLabel;
    private JLabel activeThreadsLabel;
    private JPanel footerPanel;

    public FooterPanel(JLabel activeThreadsLabel, JLabel operationInfoLabel) {
        this.activeThreadsLabel = activeThreadsLabel;
        this.operationInfoLabel = operationInfoLabel;
        this.operationInfoLabel.setText("Точек 0");
        this.footerPanel = new JPanel();
        footerPanel.setSize(new Dimension(SIZE_600, SIZE_20));
        footerPanel.setPreferredSize(new Dimension(SIZE_600, SIZE_20));
        footerPanel.setBackground(Color.GRAY);
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        this.footerPanel.add(operationInfoLabel,BorderLayout.EAST);
        this.footerPanel.add(activeThreadsLabel,BorderLayout.WEST);
    }

    public FooterPanel setOperationInfoLabel(JLabel operationInfoLabel) {
        this.operationInfoLabel = operationInfoLabel;
        return this;
    }

    public JLabel getActiveThreadsLabel() {
        return activeThreadsLabel;
    }

    public FooterPanel setActiveThreadsLabel(JLabel activeThreadsLabel) {
        this.activeThreadsLabel = activeThreadsLabel;
        return this;
    }

    public JPanel getFooterPanel() {
        return footerPanel;
    }

    public JLabel getOperationInfoLabel() {
        return operationInfoLabel;
    }

    public FooterPanel setFooterPanel(JPanel footerPanel) {
        this.footerPanel = footerPanel;
        return this;
    }
}
