package main.gui.listener;

import main.gui.UILogger;

public class AbstractLogger {
    private UILogger uiLogger;

    public AbstractLogger(UILogger uiLogger) {
        this.uiLogger = uiLogger;
    }

    public void log(String text) {
        uiLogger.logEvent(text);
    }
}
