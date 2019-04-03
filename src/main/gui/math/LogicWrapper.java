package main.gui.math;

import main.core.IAlgorithm;
import main.core.math.SystemCoordinateGenerator;
import main.core.objects.CoordinateSystem;

public class LogicWrapper {
    /**
     * Флаг остановки процесса вычисления (генерации точек, подсчёта плотности)
     */
    private volatile boolean stopAction;
    /**
     * Система координат
     */
    private CoordinateSystem coordinateSystem;
    /**
     * Генератор точек для системы координат
     */
    private SystemCoordinateGenerator systemCoordinateGenerator;
    /**
     * Выбранный алгоритм работы
     */
    private IAlgorithm algorithm;

    /**
     * Минимальное кол-во точек в окружности. Позволяет отбрасывать окружности с малым кол-вом точек внутри
     * По умолчанию 2
     */
    private Long minimalPointInCircle = 2L;

    public Long getMinimalPointInCircle() {
        return minimalPointInCircle;
    }

    public LogicWrapper setMinimalPointsInCircle(Long minimalPointInCircle) {
        this.minimalPointInCircle = minimalPointInCircle;
        return this;
    }

    public boolean isStopAction() {
        return stopAction;
    }

    public LogicWrapper setStopAction(boolean stopAction) {
        this.stopAction = stopAction;
        return this;
    }

    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
    }

    public LogicWrapper setCoordinateSystem(CoordinateSystem coordinateSystem) {
        this.coordinateSystem = coordinateSystem;
        return this;
    }

    public SystemCoordinateGenerator getSystemCoordinateGenerator() {
        return systemCoordinateGenerator;
    }

    public LogicWrapper setSystemCoordinateGenerator(SystemCoordinateGenerator systemCoordinateGenerator) {
        this.systemCoordinateGenerator = systemCoordinateGenerator;
        return this;
    }

    public IAlgorithm getAlgorithm() {
        return algorithm;
    }

    public LogicWrapper setAlgorithm(IAlgorithm algorithm) {
        this.algorithm = algorithm;
        return this;
    }
}
