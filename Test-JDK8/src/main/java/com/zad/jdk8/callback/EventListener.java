package com.zad.jdk8.callback;


/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-14 15:06
 */
public interface EventListener extends java.util.EventListener {
    /**
     * xxx
     * @param event
     */
    void handleEvent(EventObject event);
}
