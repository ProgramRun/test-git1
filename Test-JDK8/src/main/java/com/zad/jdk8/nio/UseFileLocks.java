package com.zad.jdk8.nio;// $Id$

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UseFileLocks {
    static private final int start = 10;
    static private final int end = 20;

    static public void tset() {
        // Get file channel
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile("usefilelocks.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel fc = raf.getChannel();

        // Get lock
        System.out.println("trying to get lock" + Thread.currentThread().getName());
        FileLock lock = null;
        try {
            lock = fc.lock(start, end, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("got lock!" + Thread.currentThread().getName());

        // Pause
        System.out.println("pausing");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
        }

        // Release lock
        System.out.println("going to release lock");
        try {
            lock.release();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("released lock");

        try {
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.submit(() -> tset());
        es.submit(() -> tset());
        es.submit(() -> tset());
        es.shutdown();
    }
}
