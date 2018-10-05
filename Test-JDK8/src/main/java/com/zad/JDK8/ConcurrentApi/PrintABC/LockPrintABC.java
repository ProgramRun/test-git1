package com.zad.JDK8.ConcurrentApi.PrintABC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-04 20:08
 */
public class LockPrintABC {
    private static final Lock lock = new ReentrantLock();

    private static int stat = 0;

    public static void main(String[] args) {

        PrintA pa = new PrintA(lock);
        PrintB pb = new PrintB(lock);
        PrintC pc = new PrintC(lock);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(pa);
        executorService.submit(pb);
        executorService.submit(pc);

        executorService.shutdown();
    }

    static class PrintA implements Runnable {
        private Lock lock;


        PrintA(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    System.out.println("before lock " + i);
                    lock.lock();
                    System.out.println("after lock " + i);
                    while (stat % 3 == 0) {
                        System.out.println("A" + i);
                        stat++;
                        i++;// i++放在这里是因为,只有真正打印了一次A后才应该算作一次循环,否则,i就无法用于控制A的打印次数
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }


    static class PrintB implements Runnable {
        private Lock lock;


        PrintB(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (stat % 3 == 1) {
                        System.out.println("B" + i);
                        stat++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }

            }
        }
    }


    static class PrintC implements Runnable {
        private Lock lock;


        PrintC(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (stat % 3 == 2) {
                        System.out.println("C" + i);
                        stat++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
