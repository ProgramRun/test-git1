package com.zad.jedis;

/**
 * 一个代表性别的枚举类
 */
public enum Gender {
    /**
     * 男性
     */
    MAN(1, "man"),
    /**
     * 女性
     */
    WOMAN(0, "woman");

    private Integer index;
    private String code;

    Gender(Integer index, String code) {
        this.index = index;
        this.code = code;
    }

    public Integer getIndex() {
        return index;
    }

    public String getCode() {
        return code;
    }
}
