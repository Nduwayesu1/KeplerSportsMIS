package com.example.KeplerSportsMIS.Enum;
public enum Points {
    THREE_POINTS(3),
    ONE_POINT(1),
    NO_POINTS(0);

    private final int value;

    Points(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Points fromValue(int value) {
        for (Points point : Points.values()) {
            if (point.getValue() == value) {
                return point;
            }
        }
        throw new IllegalArgumentException("Invalid point value: " + value);
    }
}
