package com.zad.jdk8.collectionTest;

import com.google.common.base.Strings;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author waiter
 * @Date 2020/7/2 0002 19:37
 * @Version 1.0
 * @Description
 */
@Slf4j
public class FastUtilTest {

    @Test
    public void listTest() {
        IntList il = new IntArrayList(100);
        il.add(1);
        il.add(2);
        il.add(3);
        il.add(4);
        il.add(5);
        log.info(il.toString());
        Assertions.assertTrue(il.contains(2));
    }

    @Test
    public void mapTest() {
        Int2ObjectMap<String> im = new Int2ObjectArrayMap<>(100);
        im.put(1, "a");
        im.put(2, "b");

        im.forEach((k, v) -> log.info(Strings.lenientFormat("key is %s, value is %s", k, v)));
        im.forEach((k, v) -> System.out.println("key is " + k + ", value is " + v));

        log.info("default return value is " + im.defaultReturnValue());
        im.defaultReturnValue("just_so_so");
        log.info("default return value is " + im.defaultReturnValue());
    }
}
