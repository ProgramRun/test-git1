package com.zad.jdk8.sort;


import java.util.Arrays;

import static com.zad.jdk8.sort.SortUtils.print;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-01-28 14:39
 */
public class BubbleSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        int last = unsorted.length;
        boolean swap;
        do {
            swap = false;
            for (int i = 0; i < last - 1; i++) {
                if (SortUtils.less(unsorted[i + 1], unsorted[i])) {
                    swap = SortUtils.swap(unsorted, i, i + 1);
                }
            }
            last--;
        } while (swap);
        return unsorted;
    }

    public static void main(String[] args) {

        // Integer Input
        Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(integers);

        // Output => 231, 78, 54, 23, 12, 9, 6, 4, 1
        print(Arrays.asList(integers));

        // String Input
        String[] strings = {"c", "a", "e", "b", "d"};
        //Output => e, d, c, b, a
        bubbleSort.sort(strings);
        print(Arrays.asList(strings));

    }
}
