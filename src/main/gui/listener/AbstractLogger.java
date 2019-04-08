package main.gui.listener;

import main.gui.UILogger;

/**
 * Класс для логирования (в правую панель приложения)
 */
public class AbstractLogger {
    private UILogger uiLogger;

    public AbstractLogger(UILogger uiLogger) {
        this.uiLogger = uiLogger;
    }

    public void log(String text) {
        uiLogger.logEvent(text);
    }
}
