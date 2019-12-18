package com.zad.jdk8.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class PinYinUtilTest {

    @Test
    void getPingYin() {
        System.out.println(PinYinUtil.getPinYin("你好"));
        System.out.println(PinYinUtil.getPinYin("你好ccc,china"));
        System.out.println(PinYinUtil.getPinYinHeadChar("你好ccc,china"));
    }

    @Test
    void getPinYinInitialLetter() throws ClassNotFoundException {
        System.out.println(PinYinUtil.getPinYinHeadChar("你好ccc,china"));
        System.out.println(PinYinUtilTest.class.getClassLoader().loadClass("com.zad.jdk8.util.DateTimeUtilTest").getSimpleName());
    }

    @Test
    void t3() {
        log.info("res is => " + tableSizeFor(10));
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n;
    }
}