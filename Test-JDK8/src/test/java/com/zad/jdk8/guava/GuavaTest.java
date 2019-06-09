package com.zad.jdk8.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.sun.istack.internal.NotNull;
import com.zad.jdk8.common.Gender;
import com.zad.jdk8.common.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-06-09 8:41
 */
@Slf4j
public class GuavaTest {

    private static List<Person> persons = null;
    private static List<String> strings = null;

    @BeforeAll
    static void initStatic() {
        Person p1 = Person.builder().givenName("p1").surName("p1").age(1).gender(Gender.WOMAN).build();
        Person p2 = Person.builder().givenName("p2").surName("p2").age(2).gender(Gender.WOMAN).build();
        Person p3 = Person.builder().givenName("p3").surName("p3").age(3).gender(Gender.WOMAN).build();
        Person p4 = Person.builder().givenName("p4").surName("p4").age(4).gender(Gender.WOMAN).build();
        Person p5 = Person.builder().givenName("p5").surName("p5").age(5).gender(Gender.WOMAN).build();

        persons = Lists.newArrayList(p1, p2, p3, p4, p5);

        strings = Lists.newArrayList("a1", "a12", "a123", "a1234");
    }

    @BeforeEach
    void init() {
    }

    @Test
    void optionalTest() {
        Optional<Integer> op1 = Optional.ofNullable(1);
        Optional<Integer> op2 = Optional.ofNullable(1);
        log.info("res is -> {}", op1.get() + op2.get());
    }

    @Test
    void preConditionTest() {
        //Preconditions.checkNotNull(null);
        Preconditions.checkArgument(0==1,"条件不和合适");
    }

    @Test
    void orderTest() {
        Ordering<String> lengthOrder = new Ordering<String>() {
            @Override
            public int compare(@NotNull String left, @NotNull String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        strings.sort(lengthOrder);
        log.info("ordered strings is -> {}", strings);
    }

    @Test
    void chainingTest() {
        strings.add(null);
        Ordering<String> lengthOrder = Ordering.natural().reverse().nullsLast().compound((String left, String right) -> Ints.compare(left.length(), right.length()));
        strings.sort(lengthOrder);
        log.info("ordered strings is -> {}", strings);
    }

    @Test
    void applicationTest() {
        Ordering<String> lengthOrder = new Ordering<String>() {
            @Override
            public int compare(@NotNull String left, @NotNull String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        log.info("min is -> {}", lengthOrder.min("1", "12", "123", "1234"));
        log.info("max is -> {}", lengthOrder.max("1", "123", "1234"));

    }
}
