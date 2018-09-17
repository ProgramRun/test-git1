package com.zad.JDK8.lambda;


import com.zad.JDK8.common.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 描述:
 * ComparatorTest
 *
 * @author zad
 * @create 2018-09-09 22:46
 */
public class ComparatorTest {
    public static void main(String[] args) {
        assert 1==2;

        List<Person> persons = new ArrayList<>(10);
        persons.add(Person.builder().surName("aaa").build());
        persons.add(Person.builder().surName("abb").build());
        persons.add(Person.builder().surName("abc").build());

        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getSurName().compareTo(p2.getSurName());
            }
        });


        System.out.println("=== Sorted Asc SurName ===");
        for (Person p : persons) {
            System.out.println(p.getSurName());
        }

        // Use Lambda instead

        // Print Asc
        System.out.println("=== Sorted Asc SurName ===");
        //Collections.sort(persons, (Person p1, Person p2) -> p1.getSurName().compareTo(p2.getSurName()));
        Collections.sort(persons, Comparator.comparing(Person::getSurName));

        for (Person p : persons) {
            System.out.println(p.getSurName());
        }

        // Print Desc
        System.out.println("=== Sorted Desc SurName ===");
        //Collections.sort(persons, (p1, p2) -> p2.getSurName().compareTo(p1.getSurName()));
        Collections.sort(persons, Comparator.comparing(Person::getSurName).reversed());

        for (Person p : persons) {
            System.out.println(p.getSurName());
        }
    }
}
