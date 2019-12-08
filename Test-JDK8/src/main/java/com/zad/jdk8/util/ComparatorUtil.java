package com.zad.jdk8.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;

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


    public static <T extends Comparable<? super T>> int comparatorDesc(T c1, T c2) {
        return ObjectUtils.compare(c2, c1);
    }

    public static <T extends Comparable<? super T>> int comparatorDesc(T c1, T c2, boolean nullGreater) {
        return ObjectUtils.compare(c2, c1, nullGreater);
    }


    public static <T extends Comparable<? super T>> int comparatorAsc(T c1, T c2) {
        return ObjectUtils.compare(c1, c2, true);
    }


    public static <T extends Comparable<? super T>> int comparatorAsc(T c1, T c2, boolean nullGreater) {
        return ObjectUtils.compare(c1, c2, nullGreater);
    }

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(null, 1, null, 2, 5, 9, 3);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return comparatorAsc(o1, o2);
            }
        });

        System.out.println(list);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return comparatorDesc(o1, o2);
            }
        });

        System.out.println(list);
    }
}
