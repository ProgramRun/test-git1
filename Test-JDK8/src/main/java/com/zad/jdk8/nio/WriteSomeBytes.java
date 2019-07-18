package com.zad.jdk8.nio;// $Id$

import com.zad.jdk8.util.JodaTimeUtil;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import static com.zad.jdk8.util.JodaTimeUtil.DEFAULT_DATE_TIME;

public class WriteSomeBytes {
	static private final byte message[] = {83, 111, 109, 101, 32,
			98, 121, 116, 101, 115, 46};

	static public void main(String args[]) throws Exception {
		Path path = Paths.get(WriteSomeBytes.class.getResource("/res.txt").toURI());
		Date time = JodaTimeUtil.getSpecificDateTime(2019, 05, 01, 0, 0, 0, 0);
		FileOutputStream fou = new FileOutputStream(path.toFile());
		FileChannel fc1 = fou.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		IOUtils.write("nice to meet you".getBytes(),System.out);

		for (int i = 1; i <= 1000; i++) {
			String s = "INSERT INTO user( age, password, id, user_name, create_datetime) VALUES (25, '5555', " + i + "," + "'zad" + i + "','" + JodaTimeUtil.dateToStr(time, DEFAULT_DATE_TIME) + "');\n";
			time = JodaTimeUtil.plusMinutes(time, 100);
			buffer.put(s.getBytes());
			buffer.flip();
			fc1.write(buffer);
			buffer.clear();
		}

		fc1.close();
	}
}
