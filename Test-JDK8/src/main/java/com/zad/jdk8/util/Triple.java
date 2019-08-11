package com.zad.jdk8.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-08-10 8:45
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class Triple<T> {
    @NonNull
    private T first;
    @NonNull
    private T second;
    @NonNull
    private T third;

}
