package com.zad.jdk8.sort;

import static com.zad.jdk8.sort.SortUtils.print;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-01-28 15:15
 */
public class InsertionSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        for (int i = 1; i < unsorted.length; i++) {
            T key = unsorted[i];
            int j = i - 1;
            while (j >= 0 && SortUtils.less(key, unsorted[j])) {
                unsorted[j + 1] = unsorted[j];
                j--;
            }
            unsorted[j + 1] = key;
        }
        return unsorted;
    }

    public static void main(String[] args) {
        // Integer Input
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};

        InsertionSort sort = new InsertionSort();

        sort.sort(integers);

        // Output => 1 4 6 9 12 23 54 78 231
        print(integers);

        // String Input
        String[] strings = {"c", "a", "e", "b","d"};

        sort.sort(strings);

        //Output => a	b	c	d	e
        print(strings);
    }
}
