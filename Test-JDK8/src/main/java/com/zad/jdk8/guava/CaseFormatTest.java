package com.zad.jdk8.guava;

import com.google.common.base.CaseFormat;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-07 9:58
 */
public class CaseFormatTest {
    public static void main(String[] args) {
        String s1 = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,"host");
        String s2 = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,"antMatchers");
        String s3 = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,"antMatchers");
        String s4 = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,"antMatchers");
        System.out.println(s1);
    }
}
