package com.zad.jdk8.DateTimeTest;

import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述: java本身api测试
 *
 * @author zad
 * @create 2018-10-05 15:10
 */
public class JavaTest {

    private static final CountDownLatch count = new CountDownLatch(2);

    private static final CyclicBarrier count2 = new CyclicBarrier(5);

    @Test
    public void testCountDownLatch() throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            es.execute(() -> {
                System.out.println(UUID.randomUUID());
                count.countDown();
            });
        }
        es.shutdown();
        count.await();
        System.out.println("ha ha~~");
    }


    @Test
    public void testInt2String() {
        int[] intArr = new int[1000000];
        String[] strArr1 = new String[1000000];

        Long s0 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            intArr[i] = 65536 + i;
        }
        Long e0 = System.currentTimeMillis();

        Long s1 = e0;
        for (int i = 0; i < 1000000; i++) {
            strArr1[i] = String.valueOf(intArr[i]);
        }
        Long e1 = System.currentTimeMillis();

        Long s2 = e1;
        for (int i = 0; i < 1000000; i++) {
            strArr1[i] = Integer.toString(intArr[i]);
        }
        Long e2 = System.currentTimeMillis();

        Long s3 = e2;
        for (int i = 0; i < 1000000; i++) {
            strArr1[i] = intArr[i] + "";
        }
        Long e3 = System.currentTimeMillis();

        System.out.println("s0 = " + s0);
        System.out.println("e0 = " + e0);
        System.out.println("s1 = " + s1);
        System.out.println("e1 = " + e1);
        System.out.println("s2 = " + s2);
        System.out.println("e2 = " + e2);
        System.out.println("s3 = " + s3);
        System.out.println("e3 = " + e3);
        System.out.println("String.valueOf(i) : " + (e1 - s1));
        System.out.println("Integer.toString(i) : " + (e2 - s2));
        System.out.println("num + \"\" : " + (e3 - s3));
    }
}



