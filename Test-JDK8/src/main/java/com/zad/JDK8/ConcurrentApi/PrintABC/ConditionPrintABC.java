package com.zad.JDK8.ConcurrentApi.PrintABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-04 20:53
 */
public class ConditionPrintABC {
    public static void main(String[] args) {
        //共用的对象，保证是同个锁
        final PrintABC printABC = new PrintABC();

        new Thread(() ->
                printABC.printA()
        ).start();
        new Thread(() ->
                printABC.printB()
        ).start();
        new Thread(() ->
                printABC.printC()
        ).start();
    }

}

class PrintABC {
    private final ReentrantLock lock = new ReentrantLock();
    //通知打印A的信号
    private Condition printA = lock.newCondition();
    //通知打印B的信号
    private Condition printB = lock.newCondition();
    //通知打印C的信号
    private Condition printC = lock.newCondition();
    //控制打印第一次的打印值
    private int flag = 0;

    public void printA() {
        for (int i = 0; i < 5; i++) {
            lock.lock();
            try {
                //循环检测标志，不是0，不发出打印A信号
                while (flag != 0) {
                    System.out.println("in");
                    printA.await();
                }
                System.out.println("A");
                flag = 1;
                //发出打印b的信号
                printB.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printB() {
        for (int i = 0; i < 5; i++) {
            lock.lock();
            try {
                while (flag != 1)
                    printB.await();

                System.out.println("B");
                flag = 2;
                printC.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

    public void printC() {
        for (int i = 0; i < 5; i++) {
            lock.lock();
            try {
                while (flag != 2)
                    printC.await();
                System.out.println("C");
                flag = 0;
                printA.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
