package com.zad.jdk8.common;

import org.apache.commons.lang3.StringUtils;

public enum Gender {
    /**
     * 女性
     */
    WOMAN(0, "woman") {
    },
    /**
     * 男性
     */
    MAN(1, "man") {
    };

    private int code;
    private String name;

    Gender(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public boolean is(String gender) {
        return StringUtils.equals(name, gender);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(Gender.MAN.is("man"));
        System.out.println(Gender.MAN.is("woman"));
        System.out.println(Gender.MAN.is(""));
        System.out.println(Gender.WOMAN.is(""));
        System.out.println(Gender.WOMAN.is("man"));
        System.out.println(Gender.WOMAN.is("woman"));
    }
}
