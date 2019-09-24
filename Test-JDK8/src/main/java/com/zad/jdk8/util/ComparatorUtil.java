package com.zad.jdk8.util;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zad
 * @date 2019/8/29 15:41
 * @description 防止空指针异常的比较器
 */
public final class ComparatorUtil {
    private ComparatorUtil() {
        throw new UnsupportedOperationException();
    }


    public static <T> int comparatorDesc(Comparable<T> c1, T c2) {
        if (c1 == null && c2 == null) {
            return 0;
        }

        if (c1 == null) {
            return 1;
        }

        if (c2 == null) {
            return -1;
        }

        return -c1.compareTo(c2);
    }

    public static <T>int comparatorAsc(Comparable<T> c1, T c2) {
        return -comparatorDesc(c1, c2);
    }


    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(null,1, null,2, 5, 9, 3);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return comparatorAsc(o1, o2);
            }
        });

        System.out.println(list);
    }
}
