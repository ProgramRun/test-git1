package com.zad.jdk8.util;

import java.util.regex.Pattern;

/**
 * 描述:
 * 各种正则表达式枚举
 *
 * @author zad
 * @create 2018-11-05 13:18
 */
public enum RegexEnum {
    /**
     * 汉字正则表达式
     */
    CHINESE_CHARACTER_PATTERN("汉字正则表达式", Pattern.compile("[\\u4E00-\\u9FA5]+")),
    /**
     * 字母数字正则表达式
     */
    CHARACTER_DIGITS_PATTERN("字母数字表达式", Pattern.compile("[a-zA-Z0-9]+")),
    /**
     * 字母正则表达式
     */
    CHARACTER_PATTERN("字母正则表达式", Pattern.compile("[a-zA-Z]+")),
    /**
     * 数字正则表达式
     */
    DIGITS_PATTERN("数字正则表达式", Pattern.compile("[0-9]+"));

    private String regexName;
    private Pattern pattern;

    RegexEnum(String regexName, Pattern pattern) {
        this.regexName = regexName;
        this.pattern = pattern;
    }

    public String getRegexName() {
        return regexName;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
