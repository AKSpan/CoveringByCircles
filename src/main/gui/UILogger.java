package main.gui;

/**
 * Класс логирующий в текстовый компонент правой формы
 */
public class UILogger {
    private ApplicationUIComponentsHolder uiComponentsHolder;

    public UILogger(ApplicationUIComponentsHolder uiComponentsHolder) {
        this.uiComponentsHolder = uiComponentsHolder;
    }

    public void logEvent(String text) {
        String currentText = this.uiComponentsHolder.getRightPanel().getTextArea().getText();
        this.uiComponentsHolder.getRightPanel().getTextArea().setText(currentText + "\n" + text);
    }
}
