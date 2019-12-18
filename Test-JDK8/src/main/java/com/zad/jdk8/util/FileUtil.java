package com.zad.jdk8.util;


import java.util.StringTokenizer;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-07-16 19:06
 */
public class FileUtil {

    public static void main(String[] args) {
        System.out.println(removeBreakingWhitespace("ass sdfa fdsf"));
    }

    static String removeBreakingWhitespace(String original) {
        StringTokenizer whitespaceStripper = new StringTokenizer(original);
        StringBuilder builder = new StringBuilder();
        while (whitespaceStripper.hasMoreTokens()) {
            builder.append(whitespaceStripper.nextToken());
            builder.append(" ");
        }
        return builder.toString();
    }
}
