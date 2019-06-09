package com.zad.jdk8.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-06-09 9:48
 */
public final class MapConverter {
    private MapConverter() {
        throw new AssertionError("禁止实例化");
    }

    public static <T, R> Map<R, T> convertNormal(List<T> list, Function<T, R> function) {
        if (list == null) {
            return null;
        }
        Map<R, T> map = Maps.newHashMap();
        for (T entity : list) {
            map.put(function.apply(entity), entity);
        }
        return map;
    }

    public static <T, R> Map<R, List<T>> convertList(List<T> list, Function<T, R> function) {
        if (list == null) {
            return null;
        }
        Map<R, List<T>> map = Maps.newHashMap();
        for (T entity : list) {
            R r = function.apply(entity);
            List<T> res = map.get(r);
            if (res == null) {
                map.put(r, Lists.newArrayList(entity));
            } else {
                res.add(entity);
            }
        }
        return map;
    }
}
