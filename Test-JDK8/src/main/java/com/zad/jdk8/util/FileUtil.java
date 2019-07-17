package com.zad.jdk8.util;



/**
 * 描述:
 *
 * @author zad
 * @create 2019-07-16 19:06
 */
public class FileUtil {
    /*public FileUtil() {
        throw new AssertionError("Util禁止反射实例化");
    }*/

    public static void main(String[] args) {
        System.out.println(FileUtil.class.getResource(""));
        System.out.println(FileUtil.class.getResource("/"));
    }
}
