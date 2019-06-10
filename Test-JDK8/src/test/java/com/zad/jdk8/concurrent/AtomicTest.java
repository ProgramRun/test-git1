package com.zad.jdk8.concurrent;

import com.zad.jdk8.util.JodaTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.nio.IntBuffer;


/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-04 19:59
 */
class AtomicTest {

    private static volatile int count = 0;


    @Test
    void t1() {
        System.err.println(JodaTimeUtil.strToDate("2019-02-15 19:57:43"));
    }


    @Test
    void atomicIntegerTest() {
        String s1 = null;
        String s2 = "s";
        System.out.println(StringUtils.equals(s1, s2));
    }

    @Test
    void t3() {
        IntBuffer ib = IntBuffer.allocate(1024);

        for (int i = 0; i < 1024; i++) {
            ib.put(i);
        }

        ib.flip();

        while (ib.hasRemaining()) {
            System.err.println(ib.get());
        }
    }

    @Test
    void t4(){
        IntBuffer intBuffer = IntBuffer.allocate(2);
        intBuffer.put(1);
        intBuffer.put(2);
        System.err.println("position: " + intBuffer.position());

        intBuffer.rewind();
        System.err.println("position: " + intBuffer.position());
        intBuffer.put(1);
        intBuffer.put(2);
        System.err.println("position: " + intBuffer.position());


        intBuffer.flip();
        System.err.println("position: " + intBuffer.position());
        intBuffer.get();
        intBuffer.get();
        System.err.println("position: " + intBuffer.position());

        intBuffer.rewind();
        System.err.println("position: " + intBuffer.position());
    }

    @Test
    void t5(){
        IntBuffer intBuffer = IntBuffer.allocate(2);
        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.flip();
        System.err.println(intBuffer.get());
        System.err.println("position: " + intBuffer.position());
        intBuffer.mark();
        System.err.println(intBuffer.get());

        System.err.println("position: " + intBuffer.position());
        intBuffer.reset();
        System.err.println("position: " + intBuffer.position());
        System.err.println(intBuffer.get());
    }
}
