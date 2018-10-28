package com.zad.JDK8.ConcurrentApi;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-07 14:30
 */
public class InterThreadCommunicationDemo {

    public static void main(String[] args) {
        final Queue sharedQ = new LinkedList<>();

        Thread producer = new Producer(sharedQ);
        Thread consumer = new Consumer(sharedQ);

        producer.start();
        consumer.start();

    }
}
