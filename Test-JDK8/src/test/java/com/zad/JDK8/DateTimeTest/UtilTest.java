package com.zad.JDK8.DateTimeTest;

import com.zad.JDK8.common.Person;
import com.zad.JDK8.util.DateTimeUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-05 15:10
 */
public class UtilTest {

    private static final CountDownLatch count = new CountDownLatch(2);

    private static final CyclicBarrier count2 = new CyclicBarrier(5);

    @Test
    public void testCountDownLatch() throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            es.execute(() -> {
                System.out.println(UUID.randomUUID());
                count.countDown();
            });
        }
        es.shutdown();
        count.await();
        System.out.println("ha ha~~");
    }

    @Test
    public void testArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.trimToSize();
        String s = "aaa";
        Class<? extends String> aClass = s.getClass();
        System.out.println(aClass.getClass().getName() + " " + aClass.getName() + " " + aClass.getSimpleName());
        ListIterator<String> li;
    }

    @Test
    public void testEqual() {
        System.out.println(Objects.equals("aaa", "aaa"));
        System.out.println(Arrays.equals(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
        System.out.println(Objects.hashCode("aaa"));
        System.out.println(Objects.hash("aaa", 111));
        System.out.println(Double.hashCode(1.111));
        System.out.println(Integer.hashCode(1));
        System.out.println(System.out);
    }

    @Test
    public void testDate() {
        Person zad = Person.builder()
                .birthday(DateTimeUtil.localDateToDate(LocalDate.of(1990, 02, 07)))
                .age(10)
                .givenName("zad1")
                .build();
        Person zad1 = Person.builder()
                .birthday(DateTimeUtil.localDateToDate(LocalDate.of(1990, 02, 07)))
                .age(9)
                .givenName("zad2")
                .build();
        Person zad2 = Person.builder()
                .birthday(DateTimeUtil.localDateToDate(LocalDate.of(1990, 02, 07)))
                .age(5)
                .givenName("zad3")
                .build();

        List<Person> plist = new ArrayList<>();
        plist.add(zad);
        plist.add(zad1);
        plist.add(zad2);
        Collections.sort(plist, Comparator.comparingInt(Person::getAge));
        System.out.println(plist);
    }

    @Test
    public void testString() {
        int[] intArr = new int[1000000];
        String[] strArr1 = new String[1000000];

        Long s0 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            intArr[i] = 65536 + i;
        }
        Long e0 = System.currentTimeMillis();

        Long s1 = e0;
        for (int i = 0; i < 1000000; i++) {
            strArr1[i] = String.valueOf(intArr[i]);
        }
        Long e1 = System.currentTimeMillis();

        Long s2 = e1;
        for (int i = 0; i < 1000000; i++) {
            strArr1[i] = Integer.toString(intArr[i]);
        }
        Long e2 = System.currentTimeMillis();

        Long s3 = e2;
        for (int i = 0; i < 1000000; i++) {
            strArr1[i] = intArr[i] + "";
        }
        Long e3 = System.currentTimeMillis();

        System.out.println("s0 = " + s0);
        System.out.println("e0 = " + e0);
        System.out.println("s1 = " + s1);
        System.out.println("e1 = " + e1);
        System.out.println("s2 = " + s2);
        System.out.println("e2 = " + e2);
        System.out.println("s3 = " + s3);
        System.out.println("e3 = " + e3);
        System.out.println("String.valueOf(i) : " + (e1 - s1));
        System.out.println("Integer.toString(i) : " + (e2 - s2));
        System.out.println("num + \"\" : " + (e3 - s3));
    }
}
