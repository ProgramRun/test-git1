package com.zad.jdk8.util;


import java.lang.reflect.Array;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-08-10 8:46
 */
@SuppressWarnings("unchecked")
public class Multiplier<T> {

    private int size;

    private T zero;

    private T first;

    private T[] other;

    public Multiplier() {
    }

    public Multiplier(T... array) {
        if (array == null || (size = array.length) == 0) {
            throw new IllegalArgumentException();
        }
        switch (size) {
            case 1:
                zero = array[0];
                return;
            case 2:
                zero = array[0];
                first = array[1];
                return;
            default:
                zero = array[0];
                first = array[1];
                other = (T[]) Array.newInstance(array[0].getClass(), array.length - 2);
                System.arraycopy(array, 2, other, 0, other.length);
        }
    }

    public void set(int index, T t) {
        switch (index) {
            case 0:
                zero = t;
                break;
            case 1:
                first = t;
                break;
            default:
                if (index > size) {
                    throw new IndexOutOfBoundsException();
                }
                other[index - 2] = t;
        }
    }

    public T get(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
        switch (index) {
            case 0:
                return zero;
            case 1:
                return first;
            default:
                return other[index - 2];
        }
    }


    public static void main(String[] args) {
        Multiplier<Integer> mo = new Multiplier(0, 1, 2, 3, 4, 5);

        mo.set(3, 8);
        mo.set(5, 95);

        for (int i = 0; i < 6; i++) {
            System.out.printf("index is %d, value is %s \r%n", i, mo.get(i));
        }
    }
}
