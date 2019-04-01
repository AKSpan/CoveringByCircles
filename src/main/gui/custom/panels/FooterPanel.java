package main.gui.custom.panels;

import javax.swing.*;

public class FooterPanel {
    private JPanel footer;
    private JLabel label;

    public FooterPanel(JPanel footer, JLabel label) {
        this.footer = footer;
        this.label = label;
    }

    public JPanel getFooter() {
        return footer;
    }

    public JLabel getLabel() {
        return label;
    }
}
