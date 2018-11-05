package com.zad.jdk8.common;

public enum Gender {
    /**
     * 女性
     */
    WOMAN(0, "woman"),
    /**
     * 男性
     */
    MAN(1, "man");

    private int code;
    private String name;

    Gender(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
