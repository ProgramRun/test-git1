package com.zad.jdk8.sort;

import java.util.BitSet;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-12-19 20:17
 */
public class BitSetTest {

    public static void main(String[] args) {
        BitSet bs = new BitSet();
        bs.set(1);
        bs.set(2);
        bs.set(3);
        bs.set(5);
        bs.set(8);
        System.out.println(bs.length());

        int length = bs.length() - 1;
        boolean valid = true;

        for (int index = length; index > 0; index--) {
            if (bs.get(index)) {
                if (LevelEnum.isTopLevel(index)) {
                    continue;
                }
                int temp = index;
                int bit = 0;
                while (temp >= 0) {
                    temp -= 1 << (bit++);
                    if (bs.get(temp)) {
                        valid = false;
                        break;
                    }
                }
            }
        }
        System.out.println(valid);
    }


    enum LevelEnum {
        FIRST(1), SECOND(2), THIRD(4), FOURTH(8);
        private int value;

        public boolean matches(int input) {
            return this.value == input;
        }

        public static boolean isTopLevel(int input) {
            for (LevelEnum levelEnum : LevelEnum.values()) {
                if (levelEnum.matches(input)) {
                    return true;
                }
            }
            return false;
        }

        LevelEnum(int value) {
            this.value = value;
        }
    }

    interface Level {
        int FIRST = 1;
        int SECOND = 1 << 1;
        int THIRD = 1 << 2;
        int FOURTH = 1 << 3;
    }
}
