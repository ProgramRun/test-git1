package com.zad.jdk8.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInteger;
import com.zad.jdk8.common.Gender;
import com.zad.jdk8.common.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-01 8:52
 */
@Slf4j
class GuavaTest {

    private static List<String> strings = null;
    private static List<Person> personList = null;

    @BeforeAll
    static void init() {
        strings = Lists.newArrayList("a1", "a12", "a12345", "a123", "a1234");

        personList = Lists.newArrayList(
                Person.builder().surName("z1").age(120).address("z1").gender(Gender.MAN).build(),
                Person.builder().surName("z2").age(1).address("z2").gender(Gender.MAN).build(),
                Person.builder().surName("z3").age(81).address("z3").gender(Gender.MAN).build(),
                Person.builder().surName("z4").age(20).address("z4").gender(Gender.WOMAN).build(),
                Person.builder().surName("z5").age(1).address("z5").gender(Gender.MAN).build());

    }


    @Test
    void optionalTest() {
        Optional<Integer> op1 = Optional.of(null);
        Optional<Integer> op2 = Optional.of(1);
        log.info("res is -> {}", op1.get() + op2.get());
    }

    @Test
    void orderingTest() {
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        strings.sort(byLengthOrdering);
        log.info("ordered list is -> {}", strings);
    }

    @Test
    void chainingTest() {
        strings.add(null);

        Ordering<String> stringOrdering = Ordering.natural().nullsLast().compound((String l, String r) -> Ints.compare(l.length(), r.length()));
        strings.sort(stringOrdering);
        log.info("orderd list is -> {}", strings);


        Ordering<String> reverseOrder = Ordering.natural().reverse().nullsLast().compound((String l, String r) -> Ints.compare(l.length(), r.length()));
        strings.sort(reverseOrder);
        log.info("orderd list is -> {}", strings);
    }


    @Test
    void multiSetTest() {
        HashMultiset.create();
    }


    @Test
    void joinerTest() {
        List<String> res=null;
        log.info("joiner test res is -> {}", Joiner.on(",").join(res));

        strings.add(null);
        log.info("joiner test res is -> {}", Joiner.on(",").skipNulls().join(strings));

        log.info("joiner test res is -> {}", Joiner.on(",").withKeyValueSeparator("=").join(ImmutableMap.of(1, 2, 3, 4)));

        Map<Integer, Integer> m1 = Maps.newHashMap();
        m1.put(1, null);
        m1.put(2, 2);
        log.info("joiner test res is -> {}", Joiner.on(",").withKeyValueSeparator("=").useForNull("null").join(m1));
    }

    @Test
    void splitterTest() {
        log.info("split res is -> {}", Splitter.on(",").omitEmptyStrings().split("1,2,3,,5"));

        log.info("split res is -> {}", Splitter.on("##").withKeyValueSeparator(":").split("1:2##3:4"));
    }

    @Test
    void charMatcher() {
        log.info("char matcher -> {}", CharMatcher.inRange('a', 'z').collapseFrom("zad1z1zzz1", '0'));
    }

    @Test
    void stringsTest() {
        log.info("null empty -> {}", Strings.isNullOrEmpty(""));

        log.info("null to empty -> {}", Strings.nullToEmpty(null));

        log.info("empty to null -> {}", Strings.emptyToNull(""));

        log.info("pad start -> {}", Strings.padStart("zad", 5, '6'));

        log.info("pad end -> {}", Strings.padStart("zad", 5, '6'));

        log.info("common prefix -> {}", Strings.commonPrefix("xiao6azzzz", "xiao6bzzz"));

        log.info("common suffix -> {}", Strings.commonSuffix("xiao6azzzz", "xiao6bzzz"));

        log.info("repeat -> {}", Strings.repeat("6", 6));
    }

    @Test
    void charMatcherTest() {
        log.info("match -> {}", CharMatcher.is('a').matchesAllOf("aaa"));
    }


    @Test
    void filterTest() {
        List<Person> oldPeople = Lists.newArrayList(Collections2.filter(personList, new Predicate<Person>() {
            public boolean apply(Person person) {
                return person.getAge() >= 20;
            }
        }));

        log.info("filter res is -> {}", oldPeople);
    }

    @Test
    void unsignedNumberTest() {
        UnsignedInteger ui1 = UnsignedInteger.valueOf("123");
        UnsignedInteger ui2 = ui1.plus(UnsignedInteger.valueOf(123));
        System.out.println(ui2);
    }

    @Test
    void setTest() {
        Set<Integer> s1 = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Integer> s2 = Sets.newHashSet(4, 5, 6, 7, 8);

        log.info("union res is -> {}", Sets.union(s1, s2));
        log.info("difference res is -> {}", Sets.difference(s1, s2));
        log.info("intersection res is -> {}", Sets.intersection(s1, s2));

    }
}
