package com.zad.jedis;


import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author zad
 * @version 1.0
 * @descript
 * @date 2019/6/18 14:47
 */
public class Test {

	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
		Person person = new Person();
		person.setAge(1);
		person.setGender(Gender.MAN);
		person.setName("zad");

		Job job = new Job();
		job.setKind("m");
		job.setName("aaa");


		Manager manager = new Manager();
		manager.setJob(job);
		manager.setPerson(person);

		ManagerDTO managerDTO = ManagerDTO.class.newInstance();
		BeanUtils.copyProperties(managerDTO, manager);
		System.out.println(managerDTO);

		PersonDTO personDTO = new PersonDTO();
		BeanUtils.copyProperties(person, personDTO);

		System.out.println(person);

		Person p1, p2;

		p1 = p2 = new Person();

		System.out.println(p1 == p2);

	}
}
