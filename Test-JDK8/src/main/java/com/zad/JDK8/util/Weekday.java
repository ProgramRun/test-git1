package com.zad.JDK8.util;

public enum Weekday {
    MON("Monday", 1),
    TUE("Tuesday", 2),
    WENS("Wensday", 3);

    private String day;
    private Integer value;

    Weekday(String day, Integer value) {
        this.day = day;
        this.value = value;
    }

    public String getDay() {
        return day;
    }

    public Integer getValue() {
        return value;
    }
}
