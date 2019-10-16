package com.zad.jdk8.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.lang.reflect.Array;
import java.util.AbstractList;

/**
 * 描述: 对象数组
 *
 * @author zad
 * @create 2019-08-10 8:46
 */
@SuppressWarnings("unchecked")
public class Multiplier<T> extends AbstractList<T> {

    private int size;

    private T zero;

    private T first;

    private T[] other;

    private Multiplier() {
    }

    public Multiplier(int size, Class<T> type) {
        if (size < 0) {
            throw new IllegalArgumentException("size cannot less than zero");
        }

        this.size = size;
        int otherSize = size - 2;
        if (otherSize > 0) {
            other = (T[]) Array.newInstance(type, otherSize);
        }
    }

    public Multiplier(T zero, T first, T... other) {
        size = other == null ? 2 : other.length + 2;
        this.zero = zero;
        this.first = first;
        this.other = other;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T set(int index, T t) {
        T old;
        switch (index) {
            case 0:
                old = zero;
                zero = t;
                break;
            case 1:
                old = first;
                first = t;
                break;
            default:
                if (index > size) {
                    throw new UnsupportedOperationException();
                }
                old = other[index - 2];
                other[index - 2] = t;
        }
        return old;
    }


    @Override
    public T get(int index) {
        switch (index) {
            case 0:
                return zero;
            case 1:
                return first;
            default:
                if (index > size) {
                    throw new IndexOutOfBoundsException();
                }
                return other[index - 2];
        }
    }

    public static void main(String[] args) {
        Multiplier<Integer> mo = new Multiplier(0, 1, 2, 3, 4, 5);

        for (Integer in : mo) {
            System.out.printf(" value is %s \r%n", in);
        }

        CharMatcher.any();
        Multiplier<Integer> mo1 = new Multiplier(1, Integer.class);

        for (Integer in : mo1) {
            System.out.printf(" value is %s \r%n", in);
        }

        Splitter.on(",").split("");
        position:
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 100; j++) {
                if (j == 10) {
                    System.out.printf("i is %d, j is %d %n", i, j);
                    continue position;
                }
            }
        }
        System.out.println("over");
    }
}
