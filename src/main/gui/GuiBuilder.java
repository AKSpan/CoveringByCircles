package main.gui;

import main.core.IAlgorithm;
import main.core.SystemCoordinateGenerator;
import main.core.TwoPointAlgorithm;
import main.core.enums.AlgorithmTypes;
import main.core.objects.CoordinateSystem;
import main.gui.custom.panels.DrawPanel;
import main.gui.listener.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GuiBuilder {
    private volatile boolean stopAction;
    private JFrame mainFrame;

    private SystemCoordinateGenerator systemCoordinateGenerator;
    private CoordinateSystem coordinateSystem;
    private DrawPanel drawPanel;
    private int drawSizeX, drawSizeY;
    private File loadedFile;
    private IAlgorithm iAlgorithm;
    private JLabel informationAboutOperationText;
    private int pointCount;

    public GuiBuilder(int x, int y, int pointCount, String title) {

        this.mainFrame = new JFrame(title);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setSize(x, y);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setLocationRelativeTo(null);
        this.pointCount = pointCount;
        this.drawSizeX = x;// / 2;
        this.drawSizeY = y;// / 2;
//        this.systemCoordinateGenerator = new SystemCoordinateGenerator(drawSizeX, drawSizeY, pointCount);
//        this.coordinateSystem = this.systemCoordinateGenerator.generate();
//        this.drawPanel = new DrawPanel(this.coordinateSystem);
        this.informationAboutOperationText = new JLabel();

        this.informationAboutOperationText.setEnabled(false);
    }


    public JFrame show() {
        this.mainFrame.setTitle("Задача по поиску максимальной плотности на площадь окружности");

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(this.createFileMenu());
        menuBar.add(this.createAlgorithmMenu());
        menuBar.add(this.createOperationMenu());
        this.mainFrame.setJMenuBar(menuBar);
        this.mainFrame.setLayout(new BorderLayout());

        JPanel footerAndDraw = new JPanel();
        footerAndDraw.setLayout(new BoxLayout(footerAndDraw, BoxLayout.Y_AXIS));


        footerAndDraw.add(this.addGraphicPanel(drawSizeX, drawSizeY));
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout());
        footer.add(this.addFooter());
        footerAndDraw.add(footer);
        this.mainFrame.add(footerAndDraw, BorderLayout.WEST);

        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setVisible(true);
        this.mainFrame.pack();

        return this.mainFrame;

    }

    private JTextField jTextField;
    private JComboBox<AlgorithmTypes> jMenuBar;

    private JPanel addFooter() {
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout());
        jTextField = new JTextField(5);
        jTextField.setText("0");
        drawPanel.addTextField(jTextField);
        JLabel label = new JLabel("Кол-во точек");
        generateButton = new JButton("Генерировать");
        generateButton.addActionListener(new GenerateButtonActionListener(this));
        calculate = new JButton("Вычислять");
        calculate.addActionListener(new CalculateDensityActionListener(this));
        JButton stopActionButton = new JButton("Стоп");
        stopActionButton.addActionListener(new StopOperationActionListener(this));


        footer.add(label);
        footer.add(jTextField);
        footer.add(generateButton);
        footer.add(calculate);
        footer.add(stopActionButton);
        footer.add(informationAboutOperationText);
        return footer;
    }


    private JPanel addGraphicPanel(int x, int y) {
        JPanel jp = new JPanel();
        jp.setLayout(null);
        jp.setLayout(new BorderLayout());

        jp.setPreferredSize(new Dimension(x, y));
        jp.setPreferredSize(new Dimension(x, y));
        this.systemCoordinateGenerator = new SystemCoordinateGenerator(drawSizeX, drawSizeY, pointCount);
        this.coordinateSystem = this.systemCoordinateGenerator.generate(this);
        this.drawPanel = new DrawPanel(this.coordinateSystem);
        drawPanel.setPreferredSize(new Dimension(x, y));
        drawPanel.setMaximumSize(new Dimension(x, y));
        jp.add(drawPanel, BorderLayout.NORTH);
        return jp;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem openItemMenu = new JMenuItem("Открыть");
        JMenuItem saveItemMenu = new JMenuItem("Сохранить");
        JMenuItem exitMenuItem = new JMenuItem("Выход");

        openItemMenu.addActionListener(new LoadButtonActionListener(this));
        saveItemMenu.addActionListener(new SaveButtonActionListener(this));
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        fileMenu.add(openItemMenu);
        fileMenu.add(saveItemMenu);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    private JMenu createAlgorithmMenu() {
        JMenu algorithm = new JMenu("Алгоритм");
        JRadioButtonMenuItem fullAlgo = new JRadioButtonMenuItem("Полный перебор");
        JRadioButtonMenuItem three_points = new JRadioButtonMenuItem("Метод 3 точек");
        JRadioButtonMenuItem two_points = new JRadioButtonMenuItem("Метод 2 точек");
        two_points.setSelected(true);
        this.setAlgorithm(new TwoPointAlgorithm());
        JRadioButtonMenuItem squares = new JRadioButtonMenuItem("Метод квадратнов");

        fullAlgo.addActionListener(new ChosenAlgorithmActionListener(this, AlgorithmTypes.FULL_SEARCH));
        three_points.addActionListener(new ChosenAlgorithmActionListener(this, AlgorithmTypes.THREE_POINTS));
        two_points.addActionListener(new ChosenAlgorithmActionListener(this, AlgorithmTypes.TWO_POINTS));
        squares.addActionListener(new ChosenAlgorithmActionListener(this, AlgorithmTypes.SQUARES_METHOD));
        ButtonGroup bg = new ButtonGroup();
        bg.add(fullAlgo);
        bg.add(three_points);
        bg.add(two_points);
        bg.add(squares);

        algorithm.add(fullAlgo);
        algorithm.add(three_points);
        algorithm.add(two_points);
        algorithm.add(squares);

        return algorithm;
    }

    private JMenu createOperationMenu() {
        JMenu operationMenu = new JMenu("Действия");
        JMenuItem clearFieldItemMenu = new JMenuItem("Очистить");
        JMenuItem minPointSetPanel = new JMenuItem("Минимальное число точек");
        clearFieldItemMenu.addActionListener(new ClearButtonActionListener(this));
        minPointSetPanel.addActionListener(new SetMinimalPointInCircleActionListener(this));
        operationMenu.add(clearFieldItemMenu);
        operationMenu.add(minPointSetPanel);

        return operationMenu;
    }

    private JButton saveData;
    private JButton generateButton;
    private JButton calculate;
    private long minPointsInCircle = 1;

    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
    }

    public IAlgorithm getAlgorithm() {
        return iAlgorithm;
    }

    public DrawPanel getDrawPanel() {
        return this.drawPanel;
    }

    public long getMinPointsInCircle() {
        return minPointsInCircle;
    }

    public void setMinPointsInCircle(long minPointsInCircle) {
        this.minPointsInCircle = minPointsInCircle;
    }

    public JTextField getTextField() {
        return jTextField;
    }

    public SystemCoordinateGenerator getSystemCoordinateGenerator() {
        return systemCoordinateGenerator;
    }

    public void setCoordinateSystem(CoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
    }

    public JComboBox<AlgorithmTypes> getjMenuBar() {
        return jMenuBar;
    }

    public void setAlgorithm(IAlgorithm iAlgorithm) {
        this.iAlgorithm = iAlgorithm;
    }

    public void setEnabledForButtons(boolean isEnable) {
        generateButton.setEnabled(isEnable);
        calculate.setEnabled(isEnable);
//        saveData.setEnabled(isEnable);
////        clear.setEnabled(isEnable);
//        generateButton.setEnabled(isEnable);
//        loadData.setEnabled(isEnable);
//        calculate.setEnabled(isEnable);
    }

    public JLabel getInformationAboutOperationText() {
        return informationAboutOperationText;
    }

    public void setInformationAboutOperationText(JLabel informationAboutOperationText) {
        this.informationAboutOperationText = informationAboutOperationText;
    }

    public boolean isStopAction() {
        return stopAction;
    }

    public synchronized void setStopAction(boolean stopAction) {
        this.setEnabledForButtons(true);
        this.stopAction = stopAction;
    }

}

