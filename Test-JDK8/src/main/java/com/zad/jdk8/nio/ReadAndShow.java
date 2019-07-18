package com.zad.jdk8.nio;// $Id$

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadAndShow {
	static public void main(String args[]) throws Exception {
		File f1 = new File("new3.txt");

		System.out.println(f1.exists());
		System.out.println(f1.isDirectory());
		System.out.println(f1.isFile());
		System.out.println(f1.getAbsolutePath());
		System.out.println(f1.getCanonicalPath());
		System.out.println(f1.getPath());

		FileInputStream fin = new FileInputStream("new3.txt");
		FileChannel fc = fin.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);

		fc.read(buffer);

		buffer.flip();

		StringBuilder sb = new StringBuilder("'");
		int LF = 10;//换行符
		int CR = 13;//回车符
		int i = 0;
		while (buffer.remaining() > 0) {
			byte b = buffer.get();

			if (b == LF) {
				sb.append(",");
				System.out.println(++i);
				continue;
			}

			if (b != CR) {
				sb.append((char) b);
			}
		}
		sb.append("'");
		fin.close();

		FileOutputStream fou = new FileOutputStream("res.txt");
		FileChannel fc1 = fou.getChannel();
		buffer.clear();
		buffer.put(sb.toString().getBytes());
		buffer.flip();
		fc1.write(buffer);

		fc1.close();
	}
}
