package main.gui;

import main.core.math.SystemCoordinateGenerator;
import main.core.TwoPointAlgorithm;
import main.core.objects.CoordinateSystem;
import main.gui.custom.panels.FooterPanel;
import main.gui.factory.JComponentsFactory;
import main.gui.listener.ApplicationActionListenerHolder;
import main.gui.math.LogicWrapper;

import javax.swing.*;
import java.awt.*;

public class GuiBuilderV2 {
    private static final int SIZE_X = 900;
    private static final int SYSTEM_COORDINATE_SIZE_X = 400;
    private static final int SIZE_Y = 600;
    private static final int SYSTEM_COORDINATE_SIZE_Y = 400;
    private static final Dimension MAIN_FRAME_SIZE = new Dimension(SIZE_X, SIZE_Y);

    /**
     * Главная форма приложения. Она наполняется компонентами.
     */
    private JFrame mainFrame;
    /**
     * Объект со всеми ключевыми компонентами формы
     */
    private ApplicationUIComponentsHolder uiComponentsHolder;

    /**
     * Объект с вычисления данных для формы
     */
    private LogicWrapper logicWrapper;

    public GuiBuilderV2() {
        this.initUI();
    }

    public GuiBuilderV2 build() {
        /* заполняем начальную логику. Алгоритм по умолчанию - две точки */
        this.logicWrapper = new LogicWrapper()
                .setStopAction(false)
                .setAlgorithm(new TwoPointAlgorithm())
                .setSystemCoordinateGenerator(new SystemCoordinateGenerator(SYSTEM_COORDINATE_SIZE_X, SYSTEM_COORDINATE_SIZE_Y, 0))
                .setCoordinateSystem(new CoordinateSystem(SYSTEM_COORDINATE_SIZE_X, SYSTEM_COORDINATE_SIZE_Y));
        /* Объект с логикой действий для кнопок и компонентов  */
        ApplicationActionListenerHolder applicationActionListenerHolder = new ApplicationActionListenerHolder(this);
        uiComponentsHolder = new ApplicationUIComponentsHolder();
        FooterPanel footerPanel1 = JComponentsFactory.getFooterPanel();
        uiComponentsHolder.setFooter(footerPanel1.getFooter());
        uiComponentsHolder.setOperationLabel(footerPanel1.getLabel());
        uiComponentsHolder.setLeftPanel(JComponentsFactory.leftPanel(applicationActionListenerHolder));
        uiComponentsHolder.setRightPanel(JComponentsFactory.rightPanel());
        uiComponentsHolder.setMenu(JComponentsFactory.menu(applicationActionListenerHolder));
        uiComponentsHolder.setDrawPanel(JComponentsFactory.drawPanel(SYSTEM_COORDINATE_SIZE_X, SYSTEM_COORDINATE_SIZE_Y, this));

        this.mainFrame.add(uiComponentsHolder.getFooter(), BorderLayout.SOUTH);
        this.mainFrame.add(uiComponentsHolder.getLeftPanel(), BorderLayout.WEST);
        this.mainFrame.add(uiComponentsHolder.getRightPanel(), BorderLayout.EAST);
        this.mainFrame.add(uiComponentsHolder.getDrawPanel(), BorderLayout.CENTER);
        this.mainFrame.setJMenuBar(uiComponentsHolder.getMenu());
        return this;
    }

    public void show() {
        /* выставляем видимость форме для отображения на экране */
        this.mainFrame.setVisible(true);
        /* "упаковка" компонентов на форме */
        this.mainFrame.pack();
    }

    private void initUI() {
        /* создаем главную форму и указываем заголовок */
        this.mainFrame = new JFrame("Поиск максимальной плотности на площадь окружности");
        /* задаем операцию по нажатию на крестик */
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /* задаем размеры формы для различных вычислений */
        this.mainFrame.setSize(MAIN_FRAME_SIZE);
        /* задаем визуальные размеры формы */
        this.mainFrame.setPreferredSize(MAIN_FRAME_SIZE);
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

    public ApplicationUIComponentsHolder getUiComponentsHolder() {
        return uiComponentsHolder;
    }

    public LogicWrapper getLogicWrapper() {
        return logicWrapper;
    }
}
