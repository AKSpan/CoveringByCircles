package main.gui;

import main.core.ExecutorServiceWrapper;
import main.core.ThreadFactory;
import main.core.math.SystemCoordinateGenerator;
import main.core.TwoPointAlgorithm;
import main.core.objects.CoordinateSystem;
import main.gui.custom.panels.FooterPanel;
import main.gui.factory.JComponentsFactory;
import main.gui.listener.ApplicationActionListenerHolder;
import main.gui.math.LogicWrapper;

import java.awt.*;

/**
 * Класс хранящий данные о приложении и запускающий его работу.
 * Отрисовывает форму, хранит события и логику
 */
public class GuiBuilderV2 {


    /**
     * Объект со всеми ключевыми компонентами формы
     */
    private ApplicationUIComponentsHolder uiComponentsHolder;

    /**
     * Объект с вычисления данных для формы
     */
    private LogicWrapper logicWrapper;

    /**
     * Сервис для работы с потоками в приложении
     */
    private ExecutorServiceWrapper executorServiceWrapper;

    public GuiBuilderV2() {

        this.executorServiceWrapper = new ExecutorServiceWrapper();
        this.uiComponentsHolder = new ApplicationUIComponentsHolder();
        this.uiComponentsHolder.initMainFormUI();

        /**********/
        this.getExecutorServiceWrapper().execute(ThreadFactory.checkThreads(this));
    }

    public GuiBuilderV2 build() {

        /* заполняем начальную логику. Алгоритм по умолчанию - две точки */
        this.logicWrapper = new LogicWrapper()
                .setStopAction(false)
                .setAlgorithm(new TwoPointAlgorithm())
                .setSystemCoordinateGenerator(new SystemCoordinateGenerator(uiComponentsHolder.getSystemCoordinateSizeX(), uiComponentsHolder.getSystemCoordinateSizeY() - uiComponentsHolder.getMarginY(), 0, uiComponentsHolder.getMarginX(), uiComponentsHolder.getMarginY()))
                .setCoordinateSystem(new CoordinateSystem(uiComponentsHolder.getSystemCoordinateSizeX(), uiComponentsHolder.getSystemCoordinateSizeY() - uiComponentsHolder.getMarginY()));
        /* Объект с логикой действий для кнопок и компонентов  */
        ApplicationActionListenerHolder applicationActionListenerHolder = new ApplicationActionListenerHolder(this);

        FooterPanel footerPanel1 = JComponentsFactory.getFooterPanel();
        uiComponentsHolder.setFooter(footerPanel1.getFooterPanel());
        uiComponentsHolder.setActiveThreadsFooter(footerPanel1.getActiveThreadsLabel());
        uiComponentsHolder.setOperationLabel(footerPanel1.getOperationInfoLabel());
        uiComponentsHolder.setLeftPanel(JComponentsFactory.leftPanel(applicationActionListenerHolder));
        uiComponentsHolder.setRightPanel(JComponentsFactory.rightPanel(applicationActionListenerHolder));
        uiComponentsHolder.setMenu(JComponentsFactory.menu(applicationActionListenerHolder));
        uiComponentsHolder.setDrawPanel(JComponentsFactory.drawPanel(this));

        this.getUiComponentsHolder().getMainFrame().add(uiComponentsHolder.getFooter(), BorderLayout.SOUTH);
        this.getUiComponentsHolder().getMainFrame().add(uiComponentsHolder.getLeftPanel(), BorderLayout.WEST);
        this.getUiComponentsHolder().getMainFrame().add(uiComponentsHolder.getRightPanel().getRightPanel(), BorderLayout.EAST);
        this.getUiComponentsHolder().getMainFrame().add(uiComponentsHolder.getDrawPanel(), BorderLayout.CENTER);
        this.getUiComponentsHolder().getMainFrame().setJMenuBar(uiComponentsHolder.getMenu());
        return this;
    }

    public void show() {
        /* фиксируем размер */
        this.getUiComponentsHolder().getMainFrame().setResizable(false);
        /* выставляем видимость форме для отображения на экране */
        this.getUiComponentsHolder().getMainFrame().setVisible(true);
        /* "упаковка" компонентов на форме */
        this.getUiComponentsHolder().getMainFrame().pack();
    }

    public ApplicationUIComponentsHolder getUiComponentsHolder() {
        return uiComponentsHolder;
    }

    public LogicWrapper getLogicWrapper() {
        return logicWrapper;
    }

    public ExecutorServiceWrapper getExecutorServiceWrapper() {
        return executorServiceWrapper;
    }
}
