package com.zad.JDK8.util;

import org.junit.jupiter.api.Test;

class PinYinUtilTest {

    @Test
    void getPingYin() {
        System.out.println(PinYinUtil.getPingYin("你好"));
        System.out.println(PinYinUtil.getPingYin("你好ccc,china"));
    }

    @Test
    void getPinYinInitialLetter() {
        System.out.println(PinYinUtil.getPinYinInitialLetter("你好ccc,china"));
    }

}