package com.zad.jdk8.guava;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInteger;
import com.zad.jdk8.common.Gender;
import com.zad.jdk8.common.Person;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
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
        strings = Lists.newArrayList("a1", "a1", "a1", "a12", "a12345", "a123", "a1234");

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
        log.info("ordered list is -> {}", strings);


        Ordering<String> reverseOrder = Ordering.natural().reverse().nullsLast().compound((String l, String r) -> Ints.compare(l.length(), r.length()));
        strings.sort(reverseOrder);
        log.info("ordered list is -> {}", strings);
    }


    @Test
    void multiSetTest() {
        HashMultiset<String> req = HashMultiset.create();
        req.addAll(strings);

        log.info("count -> {}", req.count("a1"));

        log.info("set -> {}", req.elementSet());

        TreeMultiset<String> tm = TreeMultiset.create(strings);
        log.info("count -> {}", tm.count("a1"));

        log.info("set -> {}", tm.elementSet());
    }


    @Test
    void joinerTest() {
        List<String> res = null;
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

        log.info("CaseFormat->{}", CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, null));
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

    @Test
    void doublesTest() {
        log.info("double list -> {}", Doubles.asList(1.1, 2.2, 3.3));
        log.info("doubles compare ->{}", Doubles.compare(1.1, 2.2));
        log.info("concat ->{}", Doubles.concat(new double[]{1.1, 1.1}, new double[]{2.2, 2.2}, new double[]{3.3, 3.3}));
        log.info("constrainToRange ->{}", Doubles.constrainToRange(2.5, 2.3, 2.8));
        log.info("join ->{}", Doubles.join("/", 1.1, 1.2, 1.3));
        log.info("toArray- >{}", Doubles.toArray(Lists.newArrayList(1.2, 1, 2, 1, 1.6)));
        log.info("tryParse ->{}", Doubles.tryParse("1.222"));

        double[] res = new double[]{1.1, 3.2, 3.8};
        Doubles.reverse(res);
        log.info("reverse- >{}", res);
    }

    @Test
    void enumsTest() {
        log.info("enums field ->{}", Enums.getField(Gender.MAN));
        Converter converter = Enums.stringConverter(Gender.class);
        log.info("convert ->{}", converter.convert("MAN") instanceof Gender);
    }

    @Test
    void moreObjectsTest() {
        log.info("toString -> {}", MoreObjects.toStringHelper(Person.class).add("givenName", "z").add("age", "aa").toString());
    }

    @Test
    void collections2Test() {

        log.info("transform ->{}", Collections2.transform(personList, new Function<Person, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Person person) {
                return person.getSurName();
            }
        }));


        log.info("filter -> {}", Collections2.filter(personList, new Predicate<Person>() {
            @Override
            public boolean apply(@Nullable Person person) {
                return person.getGender() == Gender.WOMAN;
            }
        }));

        log.info("orderedPermutations -> {}", Collections2.orderedPermutations(personList, new Ordering<Person>() {
            @Override
            public int compare(@Nullable Person person, @Nullable Person t1) {
                return Ints.compare(person.getAge(), t1.getAge());
            }
        }));

    }

    @Test
    void biMapTest() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("name", "zad");
        biMap.put("age", "17");

        log.info("bimap is -> {}", biMap);
        log.info("bimap is -> {}", biMap.inverse());
    }

    @Test
    void t1() {
        Table<Double, Double, Double> weightedGraph = HashBasedTable.create();
        Double d1 = 1.2;
        weightedGraph.put(d1, 2.1, 4.0);
        weightedGraph.put(d1, 2.1, 4.1);
        weightedGraph.put(d1, 2.2, 20.0);
        weightedGraph.put(d1, 2.3, 5.0);

        log.info("row map is -> {}", weightedGraph.row(d1));
        log.info("column map is -> {}", weightedGraph.column(2.1));
    }

}
