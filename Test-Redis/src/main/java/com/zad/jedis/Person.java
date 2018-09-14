package com.zad.jedis;

import lombok.Builder;
import lombok.Data;

/**
 * 描述:
 * Person
 *
 * @author zad
 * @create 2018-09-14 15:26
 */
@Data
@Builder
public class Person {
    private String name;
    private Integer age;
    private Gender gender;

}
