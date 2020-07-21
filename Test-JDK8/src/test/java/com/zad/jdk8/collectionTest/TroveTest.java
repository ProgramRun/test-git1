package com.zad.jdk8.collectionTest;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author waiter
 * @Date 2020/7/1 0001 22:40
 * @Version 1.0
 * @Description
 */
@Slf4j
public class TroveTest {

    @Test
    public void listTest() {
        TIntList intList = new TIntArrayList();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        intList.add(4);
        log.info(intList.toString());
        Assertions.assertEquals(intList.binarySearch(3), 2);
    }

    @Test
    public void mapTest() {
        TIntObjectMap<String> intObjectHashMap = new TIntObjectHashMap<>(10);
        intObjectHashMap.put(1, "a");
        intObjectHashMap.put(2, "b");
        intObjectHashMap.put(3, "c");
        log.info(intObjectHashMap.toString());
    }

}
