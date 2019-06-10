package com.zad.jdk8.util;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import com.zad.jdk8.common.Gender;
import com.zad.jdk8.common.Person;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-06-08 14:40
 */
@SuppressWarnings("unchecked")
final public class MapUtils {

    private static final String GET = "get";

    private MapUtils() {
        throw new AssertionError("Util禁止反射实例化");
    }


    public static <K, E> Map<K, E> convertOne(List<E> list, String key) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        Map<K, E> map = null;
        try {
            Method getM = getMethod(list.get(0).getClass(), key);
            map = new HashMap<>();
            for (E en : list) {
                K k = (K) getM.invoke(en);
                map.put(k, en);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static <F, T> Map<T, F> convertNormal(List<F> list, Function<F, T> function) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        Map map = new HashMap<>();
        for (F en : list) {
            T t = function.apply(en);
            map.put(t, en);
        }

        return map;
    }

    public static <F, T> ImmutableListMultimap<T, F> convertG(List<F> list, Function<F, T> function) {
        if (CollectionUtils.isEmpty(list)) {
            return ImmutableListMultimap.of();
        }
        Multimaps.index(list, function);
        return Multimaps.index(list, function);
    }


    public static <K, E> Map<K, List<E>> convertList(List<E> list, String key) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        Map<K, List<E>> map = null;
        try {
            Method getM = getMethod(list.get(0).getClass(), key);
            map = new HashMap<>();
            for (E en : list) {
                K k = (K) getM.invoke(en);
                List<E> res = map.get(k);
                if (res != null) {
                    res.add(en);
                } else {
                    List<E> l1 = new ArrayList<>();
                    l1.add(en);
                    map.put(k, l1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static <T, F> Map<T, List<F>> convertList2(List<F> list, Function<F, T> function) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        Map<T, List<F>> map = new HashMap<>();
        for (F en : list) {
            T t = function.apply(en);
            List<F> res = map.get(t);
            if (res != null) {
                res.add(en);
            } else {
                map.put(t, Lists.newArrayList(en));
            }
        }

        return map;
    }


    private static Method getMethod(Class clazz, String key) throws NoSuchMethodException {
        if (key.startsWith(GET)) {
            return clazz.getMethod(key);
        }
        if (Character.isUpperCase(key.charAt(0))) {
            clazz.getMethod(GET + key);
        }
        return clazz.getMethod(GET + Character.toUpperCase(key.charAt(0)) + key.substring(1));
    }


    public static void main(String[] args) {
        Person p1 = Person.builder().surName("z1").age(1).address("z1").gender(Gender.MAN).build();
        Person p2 = Person.builder().surName("z2").age(1).address("z2").gender(Gender.MAN).build();
        Person p3 = Person.builder().surName("z3").age(1).address("z3").gender(Gender.MAN).build();
        Person p4 = Person.builder().surName("z4").age(1).address("z4").gender(Gender.WOMAN).build();
        Person p5 = Person.builder().surName("z5").age(1).address("z5").gender(Gender.MAN).build();

        Person p11 = Person.builder().surName("z1").age(1222).address("z1").gender(Gender.MAN).build();

        List<Person> list = new ArrayList<>();

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p11);

       /* Map<String, Person> m1 = convertNormal(list, new Function<Person, String>() {
            @Override
            public String apply(@Nullable Person input) {
                return input.getSurName();
            }
        });

        System.out.println(m1);


        Map<Gender, List<Person>> m2 = convertList2(list, new Function<Person, Gender>() {
            @Override
            public Gender apply(@Nullable Person input) {
                return input.getGender();
            }
        });


        System.out.println(m2);*/


        ImmutableListMultimap<String, Person> m1 = convertG(list, new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return input.getSurName();
            }
        });

        System.out.println(m1);

    }
}
