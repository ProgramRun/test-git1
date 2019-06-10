package com.zad.jdk8.callback;

import java.util.Vector;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-14 15:08
 */
public class EventSource {
    private Vector<EventListener> ListenerList = new Vector<EventListener>();

    public void addListener(EventListener eventListener) {
        ListenerList.add(eventListener);
    }

    public void removeListener(EventListener eventListener) {
        ListenerList.remove(eventListener);
    }

    public void notifyListenerEvents(EventObject event) {
        for (EventListener eventListener : ListenerList) {
            eventListener.handleEvent(event);
        }
    }

    public static void main(String[] args) {
        EventSource eventSource = new EventSource();

        eventSource.addListener(new EventListener(){
            @Override
            public void handleEvent(EventObject event) {
                event.doEvent();
                if(event.getSource().equals("closeWindows")){
                    System.out.println("doClose");
                }
            }
        });

        eventSource.notifyListenerEvents(new EventObject("openWindows"));
        eventSource.notifyListenerEvents(new EventObject("closeWindows"));
    }
}
