package com.zad.jdk8.util;


import com.google.common.collect.Lists;

import java.util.*;

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
        return  res;
    }

    public static <T> Collection<T> removeNullElements(Collection<T> input) {
        return filter(input, new Predict<T>() {
            @Override
            public boolean test(T t) {
                return t == null;
            }
        });
    }


    public static Collection<String> removeBlankString(Collection<String> input) {
        return filter(input, new Predict<String>() {
            @Override
            public boolean test(String s) {
                return StringUtil.isBlank(s);
            }
        });
    }


    public static <T> Collection<T> filter(Collection<T> input, Predict<T> predict) {
        if (isEmpty(input)) {
            return input;
        }

        Iterator<T> it = input.iterator();

        while (it.hasNext()) {
            T temp = it.next();
            if (predict.test(temp)) {
                it.remove();
            }
        }
        return input;
    }


    public static <K, V> Map<K, V> filterKey(Map<K, V> input, Predict<K> keyPredict) {
        if (isEmpty(input)) {
            return input;
        }

        Iterator<K> it = input.keySet().iterator();
        while (it.hasNext()) {
            K tempK = it.next();
            if (keyPredict.test(tempK)) {
                it.remove();
            }
        }
        return input;
    }

    public static <K, V> Map<K, V> filterValue(Map<K, V> input, Predict<V> valuePredict) {
        if (isEmpty(input)) {
            return input;
        }
        Iterator<Map.Entry<K, V>> it = input.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> entry = it.next();
            if (valuePredict.test(entry.getValue())) {
                it.remove();
            }
        }
        return input;
    }


    public static <T, U> Collection<U> convert(Collection<T> input, Function<T, U> function) {
        if (isEmpty(input)) {
            return Collections.EMPTY_LIST;
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
            return Collections.EMPTY_MAP;
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
            return Collections.EMPTY_MAP;
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
