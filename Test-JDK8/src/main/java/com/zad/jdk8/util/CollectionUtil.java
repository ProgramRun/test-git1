package com.zad.jdk8.util;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author Binbin Wang
 * @date 2018/1/29
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

    public static List<String> removeRepeatElements(List<String> list) {
        Set<String> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

    public static List<String> removeBlankElements(List<String> list) {
        List<String> result = Lists.newArrayList();
        for (String s : list) {
            if (!Strings.isNullOrEmpty(s)) {
                result.add(s);
            }
        }
        return result;
    }


    public static <F, T> List<T> convertList(List<F> req, Function<F, T> function) {
        if (isEmpty(req)) {
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<>(req.size());
        for (F entity : req) {
            res.add(function.apply(entity));
        }
        return res;
    }


    public static <F, T> Map<T, F> convert2NormalMap(List<F> list, Function<F, T> function) {
        if (isEmpty(list)) {
            return Collections.EMPTY_MAP;
        }

        Map map = new HashMap<>(list.size());
        for (F en : list) {
            T t = function.apply(en);
            map.put(t, en);
        }
        return map;
    }


    public static <F, K, V> Map<K, V> convert2NormalMap(List<F> list, Function<F, K> keyFunction,
                                                        Function<F, V> valueFunction) {
        if (isEmpty(list)) {
            return Collections.EMPTY_MAP;
        }

        Map map = new HashMap<>(list.size());
        for (F en : list) {
            K k = keyFunction.apply(en);
            V v = valueFunction.apply(en);
            map.put(k, v);
        }
        return map;
    }


    /**
     * 使用function获取 F中的值作为key,同时将F加入map中的value list中
     *
     * @param list
     * @param function
     * @param <K>
     * @param <F>
     * @return
     */
    public static <K, F> Map<K, List<F>> convert2MapList(List<F> list, Function<F, K> function) {
        if (isEmpty(list)) {
            return Collections.EMPTY_MAP;
        }
        Map<K, List<F>> map = new HashMap<>();
        for (F en : list) {
            K t = function.apply(en);
            List<F> res = map.get(t);
            if (res != null) {
                res.add(en);
            } else {
                map.put(t, Lists.newArrayList(en));
            }
        }
        return map;
    }

    /**
     * 使用keyFunction 获取 map的key,使用valueFunction获取 map的value
     *
     * @param list
     * @param keyFunction
     * @param valueFunction
     * @param <K>
     * @param <V>
     * @param <F>
     * @return
     */
    public static <K, V, F> Map<K, List<V>> convert2MapList(List<F> list, Function<F, K> keyFunction,
                                                            Function<F, V> valueFunction) {
        if (isEmpty(list)) {
            return Collections.EMPTY_MAP;
        }
        Map<K, List<V>> map = new HashMap<>();
        for (F en : list) {
            K key = keyFunction.apply(en);
            V value = valueFunction.apply(en);
            List<V> res = map.get(key);
            if (res != null) {
                res.add(value);
            } else {
                map.put(key, Lists.newArrayList(value));
            }
        }
        return map;
    }


    public static void main(String[] args) {
        Person p1 = new Person("p1", 1, "a1");
        Person p11 = new Person("p1", 1, "a11");
        Person p2 = new Person("p2", 1, "a2");
        Person p3 = new Person("p3", 1, "a3");
        Person p4 = new Person("p4", 1, "a4");


        List<Person> people = Lists.newArrayList(p1, p11, p2, p3, p4);

        System.out.println(convert2MapList(people, new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return input.name;
            }
        }));

        System.out.println("====================");

        System.out.println(convert2MapList(people, new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return input.name;
            }
        }, new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return input.address;
            }
        }));

    }


    static class Person {
        String name;
        Integer age;
        String address;

        public Person() {
        }

        public Person(String name, Integer age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
