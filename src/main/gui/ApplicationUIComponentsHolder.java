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
    private static final int SYSTEM_COORDINATE_SIZE_X = 515;
    private static final int SYSTEM_COORDINATE_SIZE_Y = 515;
    private static final Dimension MAIN_FRAME_SIZE = new Dimension(SIZE_X, SIZE_Y);
    private JPanel footer;
    private JLabel operationLabel;
    private JPanel leftPanel;
    private RightPanel rightPanel;
    private JMenuBar menu;
    private DrawPanel drawPanel;
    private JTextField minPointInCircleTextArea;
    private JTextField squareDeepValue;
    /**
     * Главная форма приложения. Она наполняется компонентами.
     */
    private JFrame mainFrame;
    public void initMainFormUI() {
        /* поле для установки минимального значения точек в окружности */
        this.minPointInCircleTextArea = new JTextField();
        /* Поле для установки глубины разбиения на квадраты в алгоритме квандрантов*/
        this.squareDeepValue = new JTextField();
        /* создаем главную форму и указываем заголовок */
        this.mainFrame = new JFrame("Поиск максимальной плотности на площадь окружности");
        /* задаем операцию по нажатию на крестик */
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /* задаем размеры формы для различных вычислений */
        this.mainFrame.setSize(this.getMainFrameSize());
        /* задаем визуальные размеры формы */
        this.mainFrame.setPreferredSize(this.getMainFrameSize());
        /* задаем тип лэйаута для размещения компонентов */
        this.mainFrame.setLayout(new BorderLayout());

        /* вычисляем центр экрана для отображения формы по центру */
        Dimension windowSize = this.mainFrame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        this.mainFrame.setLocation(dx, dy);
    }

    public JTextField getMinPointInCircleTextArea() {
        return minPointInCircleTextArea;
    }

    public ApplicationUIComponentsHolder setMinPointInCircleTextArea(JTextField minPointInCircleTextArea) {
        this.minPointInCircleTextArea = minPointInCircleTextArea;
        return this;
    }

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

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setSquareDeepValueTextArea(JTextField squareDeepValue) {
        this.squareDeepValue=squareDeepValue;
    }

    public JTextField getSquareDeepValue() {
        return squareDeepValue;
    }
}
