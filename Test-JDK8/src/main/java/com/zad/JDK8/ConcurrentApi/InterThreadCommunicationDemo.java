package com.zad.JDK8.ConcurrentApi;

import lombok.extern.slf4j.Slf4j;

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

        Thread producer = new IProducer(sharedQ);
        Thread consumer = new IConsumer(sharedQ);

        producer.start();
        consumer.start();

    }
}


@Slf4j
class IProducer extends Thread {

    private final Queue<Integer> sharedQ;

    public IProducer(Queue sharedQ) {
        super("Producer");
        this.sharedQ = sharedQ;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++) {
            synchronized (sharedQ) {
                while (sharedQ.size() >= 1) {
                    try {
                        log.debug("Queue is full, waiting");
                        sharedQ.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("producing : " + i);
                sharedQ.add(i);
                sharedQ.notify();
            }
        }
    }
}

@Slf4j
class IConsumer extends Thread {

    private final Queue<Integer> sharedQ;

    public IConsumer(Queue sharedQ) {
        super("Consumer");
        this.sharedQ = sharedQ;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedQ) {
                while (sharedQ.size() == 0) {
                    try {
                        log.debug("Queue is empty, waiting");
                        sharedQ.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                int number = sharedQ.poll();
                log.debug("consuming : " + number);
                sharedQ.notify();
                //termination condition
                if (number == 3) {
                    break;
                }
            }
        }
    }
}

