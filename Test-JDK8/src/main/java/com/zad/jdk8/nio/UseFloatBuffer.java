package com.zad.jdk8.nio;// $Id$

import java.nio.FloatBuffer;

public class UseFloatBuffer {
    static public void main(String args[]) throws Exception {
        FloatBuffer buffer = FloatBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i) {
            float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
            buffer.put(f);
        }

        // 切换buffer读写状态(涉及到buffer设计,有可写位置,可读位置,最大尺寸)
        buffer.flip();

        while (buffer.hasRemaining()) {
            float f = buffer.get();
            System.out.println(f);
        }
    }
}
