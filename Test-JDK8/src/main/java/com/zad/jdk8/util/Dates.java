package com.zad.jdk8.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

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
        List<Ex> exList = new ArrayList<>();
    }

    static void recordIndex(){}

    @Data
    class Ex{
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
