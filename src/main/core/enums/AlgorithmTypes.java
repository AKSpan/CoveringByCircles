package main.core.enums;

/**
 * Типы алгоритмов и их названия
 */
public enum AlgorithmTypes {
    FULL_SEARCH("Полный перебор"),
    TWO_POINTS("Две точки"),
    THREE_POINTS("Три точки"),
    SQUARES_METHOD("Метод квадрантов");
    private String value;

    AlgorithmTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
