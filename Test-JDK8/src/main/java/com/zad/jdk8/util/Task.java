package com.zad.jdk8.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @author zad
 * @version 1.0
 * @descript
 * @date 2019/7/5 14:40
 */
class Task implements Runnable {
	private String name;

	public Task(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		try {
			System.out.println("Doing a task during : " + name + " - Time - " + new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		File file = new File(
				Task.class.getClassLoader().getResource("database.properties").getFile());
		System.out.println(file);

		try (FileInputStream inputStream = new FileInputStream(file)) {
			int c = inputStream.read();
			while (c > 0) {
				System.out.print((char) c);
				if (c == 13) {
					System.out.println();
				}
				c = inputStream.read();
			}
		}


	}


	private static void cal(int retryTime) {
		System.out.println(100 * (retryTime % 5));
	}

}