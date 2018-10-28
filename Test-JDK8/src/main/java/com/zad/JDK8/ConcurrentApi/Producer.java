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
public class Producer extends Thread {

    private final Queue<Integer> sharedQ;

    public Producer(Queue sharedQ) {
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
