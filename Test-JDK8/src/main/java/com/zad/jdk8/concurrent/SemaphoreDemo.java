package com.zad.jdk8.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-25 8:56
 */
public class SemaphoreDemo {

    static class Student implements Runnable {

        private int num;
        private Playground playground;

        public Student(int num, Playground playground) {
            this.num = num;
            this.playground = playground;
        }

        @Override
        public void run() {
            try {
                //获取跑道
                Playground.Track track = playground.getTrack();
                if (track != null) {
                    System.out.println("学生" + num + "使用" + track.toString());
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("学生" + num + "离开" + track.toString());
                    //释放跑道
                    playground.releaseTrack(track);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        Playground playground = new Playground();
        for (int i = 0; i < 100; i++) {
            executor.execute(new Student(i + 1, playground));
        }
        ((ExecutorService) executor).shutdown();
    }
}
