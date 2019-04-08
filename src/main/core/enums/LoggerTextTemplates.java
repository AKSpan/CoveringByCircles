package main.core.enums;

import main.core.IAlgorithm;
import main.core.objects.Point;

/**
 * Класс для логирования опредленных событий
 */
public class LoggerTextTemplates {
    private static final String ADD_POINT = "Добавлена точка с координатами [%s,%s]";
    private static final String CALCULATION_DENSITY = "Вычисляется плотность...";
    private static final String CALCULATING_PROCESS = "Отрисовка вычисленной окружности...";
    private static final String CHOSEN_ALGORITHM = "Выбран алгоритм: [%s]";

    public static String addPointEvent(Point p) {
        return String.format(ADD_POINT, p.getX(), p.getY());
    }

    public static String calculatingDensity() {
        return CALCULATION_DENSITY;
    }

    public static String calculatingProcess() {
        return CALCULATING_PROCESS;
    }

    public static String chosenAlgorithm(IAlgorithm algorithm) {
        return String.format(CHOSEN_ALGORITHM, algorithm.getAlgorithmName());
    }
}
