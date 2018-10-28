package com.zad.JDK8.guava;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-17 10:07
 */
public class CollectionsUtil {
    static Integer i1 = new Integer(999);
    static Integer i2 = new Integer(999);

    public static void main(String[] args) {
        System.out.println(sqrt(PI));
        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));
        System.out.println(Integer.toString(1996));
    }
}
