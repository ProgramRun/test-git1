package com.zad.jdk8.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-01-28 14:31
 */
public interface SortAlgorithm {
    /**
     * 排序
     *
     * @param unsorted
     * @param <T>
     * @return
     */
    <T extends Comparable<T>> T[] sort(T[] unsorted);

    default <T extends Comparable<T>> List<T> sort(List<T> unsorted) {
        return Arrays.asList(sort(unsorted.toArray((T[]) new Comparable[unsorted.size()])));
    }
}
