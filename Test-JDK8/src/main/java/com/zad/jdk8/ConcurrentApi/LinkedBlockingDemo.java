package com.zad.jdk8.ConcurrentApi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-11-04 19:09
 */
public class LinkedBlockingDemo {
    private final static ArrayBlockingQueue<Apple> queue = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        new Thread(new Producer(queue)).start();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}

class Apple {
    public Apple() {
    }
}

class Producer implements Runnable {
    private final ArrayBlockingQueue<Apple> mAbq;

    Producer(ArrayBlockingQueue<Apple> arrayBlockingQueue) {
        this.mAbq = arrayBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            Produce();
        }
    }

    private void Produce() {
        try {
            Apple apple = new Apple();
            mAbq.put(apple);
            System.out.println("生产:" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费者线程
 */
class Consumer implements Runnable {

    private ArrayBlockingQueue<Apple> mAbq;

    Consumer(ArrayBlockingQueue<Apple> arrayBlockingQueue) {
        this.mAbq = arrayBlockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                comsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void comsume() throws InterruptedException {
        Apple apple = mAbq.take();
        System.out.println("消费Apple=" + apple);
    }
}