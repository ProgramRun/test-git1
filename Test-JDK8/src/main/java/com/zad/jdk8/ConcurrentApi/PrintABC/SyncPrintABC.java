package com.zad.jdk8.ConcurrentApi.PrintABC;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-04 19:26
 */
public class SyncPrintABC {
    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        PrintCaption pa = new PrintCaption("a", c, a);
        PrintCaption pb = new PrintCaption("b", a, b);
        PrintCaption pc = new PrintCaption("c", b, c);


        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(pa);
        TimeUnit.NANOSECONDS.sleep(1);
        executorService.submit(pb);
        TimeUnit.NANOSECONDS.sleep(1);
        executorService.submit(pc);
        executorService.shutdown();
    }

    static class PrintCaption implements Runnable {
        private String caption;
        private Object preLock;
        private Object currLock;

        public PrintCaption(String caption, Object preLock, Object currLock) {
            this.caption = caption;
            this.preLock = preLock;
            this.currLock = currLock;
        }

        @Override
        public void run() {
            int i = 0;
            for (; i < 10; i++) {
                synchronized (preLock) { // 先获取 prev 锁
                    synchronized (currLock) {// 再获取 self 锁
                        System.out.println(caption + i);//打印
                        currLock.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    //此时执行完self的同步块，这时self锁才释放。
                    try {
                        if (i == 9) {
                            preLock.notifyAll();
                        } else {
                            preLock.wait(); // 立即释放 prev锁，当前线程休眠，等待唤醒
                            /**
                             * JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
                             */}
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

