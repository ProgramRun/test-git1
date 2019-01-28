package com.zad.jdk8.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 描述: 排序通用工具类
 *
 * @author zad
 * @create 2019-01-28 14:25
 */
public class SortUtils {
    static <T> boolean swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return true;
    }

    static <T extends Comparable<T>> boolean less(T x, T y) {
        return x.compareTo(y) < 0;
    }

    /**
     * Just print list
     * @param toPrint - a list which should be printed
     */
    static void print(List<?> toPrint){
        toPrint.stream()
                .map(Object::toString)
                .map(str -> str + " ")
                .forEach(System.out::print);

        System.out.println();
    }


    /**
     * Prints an array
     * @param toPrint - the array  which should be printed
     */
    static void print(Object[] toPrint){
        System.out.println(Arrays.toString(toPrint));
    }



    /**
     * Swaps all position from {@param left} to @{@param right} for {@param array}
     * @param array is an array
     * @param left is a left flip border of the array
     * @param right is a right flip border of the array
     */
    static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
        while (left <= right) {
            swap(array, left++ , right--);
        }
    }
}
