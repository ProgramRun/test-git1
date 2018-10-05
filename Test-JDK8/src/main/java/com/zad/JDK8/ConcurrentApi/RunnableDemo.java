package com.zad.JDK8.ConcurrentApi;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-04 14:38
 */

public class RunnableDemo implements Runnable {
    Thread t;
    String threadName;

    public RunnableDemo(String name) {
        this.threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);

        for (int i = 0; i < 4; i++) {
            System.out.println("Thread: " + threadName + ", " + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Thread " + threadName + " interrupted.");
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}