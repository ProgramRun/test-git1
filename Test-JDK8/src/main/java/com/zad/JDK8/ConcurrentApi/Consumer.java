package com.zad.JDK8.ConcurrentApi;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-10-07 14:30
 */
@Slf4j
public class Consumer extends Thread {

    private final Queue<Integer> sharedQ;

    public Consumer(Queue sharedQ) {
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
