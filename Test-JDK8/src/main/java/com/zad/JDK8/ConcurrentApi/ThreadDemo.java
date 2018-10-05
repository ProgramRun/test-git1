package com.zad.JDK8.ConcurrentApi;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-04 14:13
 */
class ThreadDemo extends Thread {
    private String threadName;
    private Thread t;

    public ThreadDemo(String name) {
        this.threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 0; i < 4; i++) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
