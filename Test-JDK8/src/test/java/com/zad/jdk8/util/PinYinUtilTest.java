package com.zad.jdk8.util;

import org.junit.jupiter.api.Test;

class PinYinUtilTest {

    @Test
    void getPingYin() {
        System.out.println(PinYinUtil.getPingYin("你好"));
        System.out.println(PinYinUtil.getPingYin("你好ccc,china"));
        System.out.println(PinYinUtil.getPinYinInitialLetter("你好ccc,china"));
    }

    @Test
    void getPinYinInitialLetter() throws ClassNotFoundException {
        System.out.println(PinYinUtil.getPinYinInitialLetter("你好ccc,china"));
        System.out.println(PinYinUtilTest.class.getClassLoader().loadClass("com.zad.jdk8.util.DateTimeUtilTest").getSimpleName());
    }

}