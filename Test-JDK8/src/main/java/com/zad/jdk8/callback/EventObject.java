package com.zad.jdk8.callback;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-14 15:07
 */
public class EventObject extends java.util.EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public EventObject(Object source) {
        super(source);
    }

    public void doEvent(){
        System.out.println("通知一个事件源 source :"+ this.getSource());
    }
}
