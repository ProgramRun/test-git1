package com.zad.jdk8.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-08-09 19:54
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Pair<T extends Comparable<T>> {
    @NonNull
    private T first;
    @NonNull
    private T second;

    public static void main(String[] args) {
        Pair<BigDecimal> biMoney = Pair.of(BigDecimal.ONE, BigDecimal.TEN);

        System.out.println(biMoney.getFirst());
        System.out.println(biMoney.getSecond());
    }
}
