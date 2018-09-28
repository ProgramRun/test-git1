package com.zad.chat.protocol;

/**
 * 描述:
 * aa
 *
 * @author zad
 * @create 2018-09-26 13:12
 */
public enum  IMP {
    SYSTEM("SYSTEM"),

    LOGIN("LOGIN"),

    LOGOUT("LOGOUT"),

    CHAT("CHAT"),

    FLOWER("FLOWER");

    private String name;

    public static boolean isIMP(String content) {
        return content.matches("^\\[(SYSTEM|LOGIN|LOGIN|CHAT|FLOWER)\\]");
    }

    IMP(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
