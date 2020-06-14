package com.zad.jdk8.util;


import com.google.common.collect.Lists;
import lombok.var;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author zad
 * @date 20191022
 */
public final class CollectionUtil {


    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }


    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean notEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }

    public static <K, V> boolean notEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    public static <T> Collection<T> removeRepeatElements(Collection<T> input) {
        if (isEmpty(input)) {
            return input;
        }
        Set<T> res = new HashSet<>(input.size());
        for (T entity : input) {
            if (entity != null) {
                res.add(entity);
            }
        }
        return res;
    }

    public static <T> Collection<T> removeNullElements(Collection<T> input) {
        return filter(input, new Predicate<T>() {
            @Override
            public boolean test(T t) {
                return t == null;
            }
        });
    }


    public static Collection<String> removeBlankString(Collection<String> input) {
        return input.stream().filter(StringUtil::isNotBlank).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        var l = List.of(":", "1", "", "a");
        System.out.println(removeBlankString(l));
    }

    public static <T> Collection<T> filter(Collection<T> input, Predicate<T> predicate) {
        if (isEmpty(input)) {
            return input;
        }

        input.removeIf(predicate);
        return input;
    }


    public static <K, V> Map<K, V> filterKey(Map<K, V> input, Predicate<K> keyPredicate) {
        if (isEmpty(input)) {
            return input;
        }

        input.keySet().removeIf(keyPredicate);
        return input;
    }

    public static <K, V> Map<K, V> filterValue(Map<K, V> input, Predicate<V> valuePredicate) {
        if (isEmpty(input)) {
            return input;
        }
        input.entrySet().removeIf(entry -> valuePredicate.test(entry.getValue()));
        return input;
    }


    public static <T, U> Collection<U> convert(Collection<T> input, Function<T, U> function) {
        if (isEmpty(input)) {
            return Collections.emptyList();
        }

        List<U> res = new ArrayList<>(input.size());
        for (T entity : input) {
            if (entity == null) {
                continue;
            }
            res.add(function.apply(entity));
        }
        return res;
    }


    public static <K, V, T> Map<K, V> convert2Map(Collection<T> input, Function<T, K> keyFunction,
                                                  Function<T, V> valueFunction) {
        if (isEmpty(input)) {
            return Collections.emptyMap();
        }

        Map<K, V> res = new HashMap<>(input.size());
        for (T entity : input) {
            if (entity == null) {
                continue;
            }
            K k = keyFunction.apply(entity);
            V v = valueFunction.apply(entity);
            res.put(k, v);
        }
        return res;
    }


    public static <K, V, T> Map<K, List<V>> convert2MapList(Collection<T> input, Function<T, K> keyFunction,
                                                            Function<T, V> valueFunction) {
        if (isEmpty(input)) {
            return Collections.emptyMap();
        }

        Map<K, List<V>> res = new HashMap<>(input.size());
        for (T entity : input) {
            if (entity == null) {
                continue;
            }
            K k = keyFunction.apply(entity);
            V v = valueFunction.apply(entity);
            List<V> tempValues = res.get(k);
            if (tempValues == null) {
                res.put(k, Lists.newArrayList(v));
            } else {
                tempValues.add(v);
            }
        }
        return res;
    }

}
