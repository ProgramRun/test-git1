package com.zad.jdk8.util;

/**
 * 描述:
 * String补充
 *
 * @author zad
 * @create 2019-03-05 19:58
 */
public class Strings {
    public static void main(String[] args) {
        Boolean b = true;
        changeB(b);
        System.out.println();
    }

    private static void changeB(Boolean b){
        b = false;
    }

    static int getIndex(String source, String pattern) {
        return KMPAlg(source, pattern);
    }

    static boolean isInSource(String source, String pattern) {
        int res = KMPAlg(source, pattern);
        if (res == -1) {
            return false;
        }
        return true;
    }

    private static int KMPAlg(String source, String pattern) {
        int[] stepArray = getStepArray(pattern);
        int sourceLength = source.length();
        int patternLength = pattern.length();
        for (int i = 0; i <= (sourceLength - patternLength); ) {
            String str = source.substring(i, i + patternLength);
            int count = getNext(pattern, str, stepArray);
            if (count == 0) {
                return i;
            }
            i += count;
        }
        return -1;
    }

    /**
     * 得到下一次要移动的次数
     *
     * @param pattern
     * @param str
     * @param stepArray
     * @return 0, 字符串匹配；
     */
    private static int getNext(String pattern, String str, int[] stepArray) {
        int n = pattern.length();
        char[] v1 = str.toCharArray();
        char[] v2 = pattern.toCharArray();
        int x = 0;
        while (n-- != 0) {
            if (v1[x] != v2[x]) {
                if (x == 0) {
                    return 1;
                }
                return x - stepArray[x - 1];
            }
            x++;
        }
        return 0;
    }

    private static int[] getStepArray(String pattern) {
        char[] patternArray = pattern.toCharArray();
        int last = pattern.length();
        int[] res = new int[last + 1];
        for (int i = last; i > 1; i--) {
            res[i - 1] = getStep(i, patternArray);
        }
        return res;
    }

    private static int getStep(int j, char[] patternArray) {
        int x = j - 2;
        int y = 1;
        while (x >= 0 && compare(patternArray, 0, x, y, j - 1)) {
            x--;
            y++;
        }
        return x + 1;
    }

    private static boolean compare(char[] pat, int b1, int e1, int b2, int e2) {
        int n = e1 - b1 + 1;
        while (n-- != 0) {
            if (pat[b1] != pat[b2]) {
                return true;
            }
            b1++;
            b2++;
        }
        return false;
    }
}
