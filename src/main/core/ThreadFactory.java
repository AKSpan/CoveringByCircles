package main.core;

import main.core.enums.LoggerTextTemplates;
import main.core.objects.CoordinateSystem;
import main.gui.GuiBuilderV2;
import main.gui.listener.AbstractLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Класс для предоставления определенных задач
 * Задачи используются для запуска в отдельных потоках
 */
public class ThreadFactory {


    /**
     * Генерация точек
     * @param guiBuilder
     * @return
     */
    public static Runnable generatePoint(GuiBuilderV2 guiBuilder) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                CoordinateSystem coordinateSystem = guiBuilder.getLogicWrapper().getSystemCoordinateGenerator().generate(guiBuilder);
                guiBuilder.getLogicWrapper().setCoordinateSystem(coordinateSystem);
                guiBuilder.getUiComponentsHolder().getDrawPanel().addPoints(coordinateSystem, null);
                guiBuilder.getUiComponentsHolder().setLabelText("Точек " + coordinateSystem.getPoints().size());
            }
        };
        return r;
    }

    /**
     * Вычисление плотности
     * @param guiBuilder
     * @param abstractLogger
     * @return
     */
    public static Runnable calculateDensity(GuiBuilderV2 guiBuilder, AbstractLogger abstractLogger) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                abstractLogger.log(LoggerTextTemplates.calculatingDensity());
                guiBuilder.getUiComponentsHolder().setEnabledForButtons(false);
                guiBuilder.getUiComponentsHolder().getDrawPanel().calculateDensityAndDrawCircle(guiBuilder, abstractLogger);
                guiBuilder.getUiComponentsHolder().setEnabledForButtons(true);
            }
        };
        return r;
    }

    /**
     * Проверка и отображение на форме приложения числа активных потоков
     * @param guiBuilder
     * @return
     */
    public static Runnable checkThreads(GuiBuilderV2 guiBuilder) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ExecutorService pool = guiBuilder.getExecutorServiceWrapper().getExecutorService();
                if (pool instanceof ThreadPoolExecutor) {
                    while (true) {
                        if (guiBuilder.getUiComponentsHolder().getActiveThreadsLabel() != null)
                            guiBuilder.getUiComponentsHolder().getActiveThreadsLabel().setText("Число активных потоков: " + (((ThreadPoolExecutor) pool).getActiveCount() - 1));
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        return r;
    }
}
