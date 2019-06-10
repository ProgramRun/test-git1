package com.zad.jdk8.guava;

import com.google.common.base.Optional;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-06-08 20:57
 */
public class OptionalTest {
    public static void main(String[] args) {
        Integer invalidInput = null;
        Optional<Integer> a = Optional.of(invalidInput);
        Optional<Integer> b = Optional.of(new Integer(10));
        System.out.println(sum(a, b));
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b) {
        return a.get() + b.get();
    }
}



