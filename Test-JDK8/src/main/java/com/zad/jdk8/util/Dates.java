package com.zad.jdk8.util;

import com.google.common.math.BigIntegerMath;
import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import lombok.Data;

import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-08-05 7:58
 */
public class Dates {
    private Dates() {
        throw new AssertionError("Util禁止反射实例化");
    }

    // start - end string -> date
    // 记录每天出现的index
    // 统计每天的count


    public static void main(String[] args) {
        int logFloor = LongMath.log2(12, RoundingMode.FLOOR);

        int mustNotOverflow = IntMath.checkedMultiply(2, 5);

        long quotient = LongMath.divide(20, 3, RoundingMode.UNNECESSARY); // fail fast on non-multiple of 3

        BigInteger nearestInteger = DoubleMath.roundToBigInteger(12, RoundingMode.HALF_EVEN);

        BigInteger sideLength = BigIntegerMath.sqrt(BigInteger.valueOf(11221), RoundingMode.CEILING);
    }

    static void recordIndex() {
    }

    @Data
    class Ex {
        private String start;
        private String end;
        private Integer num;

        public Ex(String start, String end, Integer num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }
    }
}
