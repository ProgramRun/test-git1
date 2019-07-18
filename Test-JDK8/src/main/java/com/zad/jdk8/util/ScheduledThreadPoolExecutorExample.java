package com.zad.jdk8.util;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zad
 * @version 1.0
 * @descript
 * @date 2019/7/5 14:41
 */
public class ScheduledThreadPoolExecutorExample {
	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		Task task1 = new Task("Demo Task 1");
		Task task2 = new Task("Demo Task 2");

		System.out.println("The time is : " + new Date());

		executor.schedule(task1, 1, TimeUnit.SECONDS);
		executor.schedule(task2, 2, TimeUnit.SECONDS);

		executor.scheduleWithFixedDelay(task1, 1, 3, TimeUnit.SECONDS);

		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("The time is : " + new Date());
		executor.shutdown();
	}
}
