package com.zad.JDK8.ConcurrentApi;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-04 19:59
 */
class AtomicTest {

    private static volatile  int count = 0;


    @Test
    void t1() {
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                public void run() {
                    count++;
                }
            }.start();
        }
        System.out.println("count: " + count);
    }


    @Test
    void atomicIntegerTest() {
        AtomicInteger ai = new AtomicInteger(0);
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }
}
