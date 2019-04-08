package main.gui.factory;

import main.core.enums.AlgorithmTypes;
import main.gui.GuiBuilderV2;
import main.gui.custom.panels.DrawPanel;
import main.gui.custom.panels.FooterPanel;
import main.gui.custom.panels.RightPanel;
import main.gui.listener.ApplicationActionListenerHolder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Класс для отрисовки заготовленных форм приложения
 */
public class JComponentsFactory {
    private final static int SIZE_20 = 20;
    private final static int SIZE_100 = 100;
    private final static int SIZE_120 = 120;
    private final static int SIZE_200 = 200;
    private final static int SIZE_600 = 600;
    private final static int SIZE_700 = 700;
    private final static int SIZE_500 = 700;

    /* Нижняя панель с информацией об операции */
    public static FooterPanel getFooterPanel() {

        JLabel operationLabel = new JLabel();
        operationLabel.setSize(60, 15);
        operationLabel.setBorder(new EmptyBorder(0, 0, 0, 15));
        operationLabel.setFont(new Font("myFont", Font.BOLD, 12));
        operationLabel.setText("");
        operationLabel.setForeground(Color.BLACK);


        JLabel threadsCountLAbel = new JLabel();
        threadsCountLAbel.setSize(60, 15);
        threadsCountLAbel.setBorder(new EmptyBorder(0, 0, 0, 15));
        threadsCountLAbel.setFont(new Font("myFont", Font.BOLD, 12));
        threadsCountLAbel.setText("");
        threadsCountLAbel.setForeground(Color.BLACK);
        return new FooterPanel(threadsCountLAbel,operationLabel);
    }

    /* Левая панель с кнопками */
    public static JPanel leftPanel(ApplicationActionListenerHolder applicationActionListenerHolder) {

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setSize(SIZE_120, SIZE_600);
        leftPanel.setPreferredSize(new Dimension(SIZE_120, SIZE_600));
        leftPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

        /* 1 */
        URL resource = JComponentsFactory.class.getClassLoader().getResource("axis.png");
        ImageIcon imageIcon = new ImageIcon(resource);
        JButton generateButton = new JButton(imageIcon);
        generateButton.setToolTipText("Генерация точек");
        generateButton.setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        generateButton.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
        generateButton.setBorderPainted(false);
        generateButton.addActionListener(applicationActionListenerHolder.getGeneratePointsActionListener());

        URL resourceCalcImg = JComponentsFactory.class.getClassLoader().getResource("calculate.png");
        ImageIcon imageIconCalc = new ImageIcon(resourceCalcImg);
        JButton calculate = new JButton(imageIconCalc);

        calculate.setToolTipText("Вычисление плотности");
        calculate.setSize(imageIconCalc.getIconWidth(), imageIconCalc.getIconHeight());
        calculate.setPreferredSize(new Dimension(imageIconCalc.getIconWidth(), imageIconCalc.getIconHeight()));
        calculate.setBorderPainted(false);
        calculate.addActionListener(applicationActionListenerHolder.getCalculateDensityActionListener());

        JPanel firstButtonRow = new JPanel();
        firstButtonRow.setLayout(new BoxLayout(firstButtonRow, BoxLayout.X_AXIS));
        firstButtonRow.add(generateButton);
        firstButtonRow.add(Box.createHorizontalStrut(15));
        firstButtonRow.add(calculate);

        /* 2 */

        JPanel secondButtonRow = new JPanel();
        secondButtonRow.setLayout(new BoxLayout(secondButtonRow, BoxLayout.X_AXIS));

        URL resourceEraseImg = JComponentsFactory.class.getClassLoader().getResource("eraser.png");

        ImageIcon imageIconErase = new ImageIcon(resourceEraseImg);
        JButton clearButton = new JButton(imageIconErase);
        clearButton.setToolTipText("Очистить");
        clearButton.setSize(imageIconErase.getIconWidth(), imageIconErase.getIconHeight());
        clearButton.setPreferredSize(new Dimension(imageIconErase.getIconWidth(), imageIconErase.getIconHeight()));
        clearButton.setBorderPainted(false);
        clearButton.addActionListener(applicationActionListenerHolder.getClearFormActionListener());

        secondButtonRow.add(clearButton);

        /* 3 */
        JPanel thirdButtonRow = new JPanel();
        thirdButtonRow.setLayout(new BoxLayout(thirdButtonRow, BoxLayout.X_AXIS));

        URL resourceSaveImg = JComponentsFactory.class.getClassLoader().getResource("diskette.png");
        URL resourceLoadImg = JComponentsFactory.class.getClassLoader().getResource("folder.png");

        ImageIcon imageIconSave = new ImageIcon(resourceSaveImg);
        ImageIcon imageIconLoad = new ImageIcon(resourceLoadImg);
        JButton saveButton = new JButton(imageIconSave);
        JButton loadButton = new JButton(imageIconLoad);
        saveButton.setToolTipText("Сохранить");
        saveButton.setSize(imageIconSave.getIconWidth(), imageIconSave.getIconHeight());
        saveButton.setPreferredSize(new Dimension(imageIconSave.getIconWidth(), imageIconSave.getIconHeight()));
        saveButton.setBorderPainted(false);
        saveButton.addActionListener(applicationActionListenerHolder.getSaveToFileActionListener());

        loadButton.setToolTipText("Загрузить");
        loadButton.setSize(imageIconLoad.getIconWidth(), imageIconLoad.getIconHeight());
        loadButton.setPreferredSize(new Dimension(imageIconLoad.getIconWidth(), imageIconLoad.getIconHeight()));
        loadButton.setBorderPainted(false);
        loadButton.addActionListener(applicationActionListenerHolder.getLoadFromFileActionListener());

        thirdButtonRow.add(saveButton);
        thirdButtonRow.add(Box.createHorizontalStrut(15));
        thirdButtonRow.add(loadButton);

        /* 4 */
        JPanel fourthButtonRow = new JPanel();
        fourthButtonRow.setLayout(new BoxLayout(fourthButtonRow, BoxLayout.X_AXIS));
        URL resourceStopImg = JComponentsFactory.class.getClassLoader().getResource("stop.png");
        ImageIcon imageIconStop = new ImageIcon(resourceStopImg);
        JButton stopButton = new JButton(imageIconStop);
        stopButton.setToolTipText("Остановить");
        stopButton.setSize(imageIconStop.getIconWidth(), imageIconStop.getIconHeight());
        stopButton.setPreferredSize(new Dimension(imageIconStop.getIconWidth(), imageIconStop.getIconHeight()));
        stopButton.setBorderPainted(false);
        stopButton.addActionListener(applicationActionListenerHolder.getStopOperationActionListener());
        fourthButtonRow.add(stopButton);
        /* 5 */
        JPanel fifthButtonRow = new JPanel();
        fifthButtonRow.setLayout(new BoxLayout(fifthButtonRow, BoxLayout.Y_AXIS));
        ButtonGroup bg = new ButtonGroup();
        JRadioButton three_points = new JRadioButton(AlgorithmTypes.THREE_POINTS.getValue());
        three_points.setPreferredSize(new Dimension(100, 15));
        three_points.setFont(new Font("BoxFont", Font.PLAIN, 8));
        JRadioButton two_points = new JRadioButton(AlgorithmTypes.TWO_POINTS.getValue());
        two_points.setPreferredSize(new Dimension(100, 15));
        two_points.setFont(new Font("BoxFont", Font.PLAIN, 8));
        JRadioButton squares = new JRadioButton(AlgorithmTypes.SQUARES_METHOD.getValue());
        squares.setPreferredSize(new Dimension(100, 15));
        squares.setFont(new Font("BoxFont", Font.PLAIN, 8));
        three_points.addActionListener(applicationActionListenerHolder.getChosenAlgorithmActionListener(AlgorithmTypes.THREE_POINTS));
        two_points.addActionListener(applicationActionListenerHolder.getChosenAlgorithmActionListener(AlgorithmTypes.TWO_POINTS));
        squares.addActionListener(applicationActionListenerHolder.getChosenAlgorithmActionListener(AlgorithmTypes.SQUARES_METHOD));
        bg.add(three_points);
        bg.add(two_points);
        bg.add(squares);
        JLabel algoLAble = new JLabel("Алгоритм");
        fifthButtonRow.add(algoLAble);
        fifthButtonRow.add(three_points);
        two_points.setSelected(true);
        fifthButtonRow.add(two_points);
        fifthButtonRow.add(squares);
        /* 6 */
        JPanel sixRow = new JPanel();
        sixRow.setLayout(new BoxLayout(sixRow, BoxLayout.Y_AXIS));
        JLabel minPointLabel = new JLabel("Число точек в окр-ти");
        minPointLabel.setFont(new Font("myFont", Font.BOLD, 10));
        sixRow.add(minPointLabel);
        JTextField minPointsText = new JTextField();
        minPointsText.setPreferredSize(new Dimension(10, 20));
        applicationActionListenerHolder.getGuiBuilder().getUiComponentsHolder().setMinPointInCircleTextArea(minPointsText);
        minPointsText.addKeyListener(applicationActionListenerHolder.getSetMinimalPointsInCircleActionListener());
        sixRow.add(minPointsText);
        /* 7 */
        JPanel sevenRow = new JPanel();
        sevenRow.setLayout(new BoxLayout(sevenRow, BoxLayout.Y_AXIS));
        JLabel squareDeepValueLabel = new JLabel("Глубина разбиения");
        squareDeepValueLabel.setFont(new Font("myFont", Font.BOLD, 9));
        sevenRow.add(squareDeepValueLabel);
        JTextField squareDeepValue = new JTextField();
        squareDeepValue.setPreferredSize(new Dimension(10, 20));
        applicationActionListenerHolder.getGuiBuilder().getUiComponentsHolder().setSquareDeepValueTextArea(squareDeepValue);
        squareDeepValue.addKeyListener(applicationActionListenerHolder.getSetSquareDeepValue());
        sevenRow.add(squareDeepValue);

        leftPanel.add(firstButtonRow);
        leftPanel.add(secondButtonRow);
        leftPanel.add(thirdButtonRow);
        leftPanel.add(fourthButtonRow);
        leftPanel.add(fifthButtonRow);
        leftPanel.add(sixRow);
        leftPanel.add(sevenRow);

        return leftPanel;
    }

    /* Правая панель */
    public static RightPanel rightPanel(ApplicationActionListenerHolder applicationActionListenerHolder) {
        JPanel rightPanel = new JPanel();
        rightPanel.setSize(SIZE_200, SIZE_700);
        rightPanel.setPreferredSize(new Dimension(SIZE_200, SIZE_600));
        rightPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));


        JTextArea textArea = new JTextArea(32, 19);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("AppFont", Font.PLAIN, 10));
        JScrollPane jScrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightPanel.add(jScrollPane);

        RightPanel panel = new RightPanel(rightPanel, textArea);

        return panel;
    }
/* Выпадающее меню */
    public static JMenuBar menu(ApplicationActionListenerHolder applicationActionListenerHolder) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(JComponentsFactory.createFileMenu(applicationActionListenerHolder));

        return menuBar;
    }
 /* графическая форма */
    public static DrawPanel drawPanel(GuiBuilderV2 guiBuilder) {
        DrawPanel drawPanel = new DrawPanel(guiBuilder);

        return drawPanel;
    }

    /* создание выпадающего меню */
    private static JMenu createFileMenu(ApplicationActionListenerHolder applicationActionListenerHolder) {
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem openItemMenu = new JMenuItem("Открыть");
        JMenuItem saveItemMenu = new JMenuItem("Сохранить");
        JMenuItem exitMenuItem = new JMenuItem("Выход");


        openItemMenu.addActionListener(applicationActionListenerHolder.getLoadFromFileActionListener());
        saveItemMenu.addActionListener(applicationActionListenerHolder.getSaveToFileActionListener());
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
}
