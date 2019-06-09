package com.zad.jdk8.util;

import com.google.common.collect.Lists;
import com.zad.jdk8.common.Gender;
import com.zad.jdk8.common.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
class MapConverterTest {

    private static List<Person> persons = null;

    @BeforeAll
    static void initStatic() {
        Person p1 = Person.builder().givenName("p1").surName("p1").age(1).gender(Gender.WOMAN).build();
        Person p2 = Person.builder().givenName("p2").surName("p2").age(2).gender(Gender.WOMAN).build();
        Person p3 = Person.builder().givenName("p3").surName("p3").age(3).gender(Gender.MAN).build();
        Person p4 = Person.builder().givenName("p4").surName("p4").age(4).gender(Gender.WOMAN).build();
        Person p5 = Person.builder().givenName("p5").surName("p5").age(5).gender(Gender.MAN).build();
        persons = Lists.newArrayList(p1, p2, p3, p4, p5);
    }

    @Test
    void convertNormal() {
        Map<String, Person> map1 = MapConverter.convertNormal(persons, new Function<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getSurName();
            }
        });

        log.info("res is -> {}", map1);
    }

    @Test
    void convertList() {
        Map<Gender, List<Person>> map2 = MapConverter.convertList(persons, new Function<Person, Gender>() {
            @Override
            public Gender apply(Person person) {
                return person.getGender();
            }
        });

        log.info("res is -> {}", map2);
    }
}