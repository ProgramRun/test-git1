package com.zad.jdk8.callback;

import java.util.Observable;
import java.util.Observer;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-14 15:52
 */
public class Watcher extends Observable {

    @Override
    public void notifyObservers(Object arg) {

        /**
         * 为避免并发冲突，设置了changed标志位changed =true，则当前线程可以通知所有观察者，内部同步块会完了会设置为false；
         通知过程中，正在新注册的和撤销的无法通知到。
         */
        super.setChanged();
        /**
         * 事件触发，通知所有感兴趣的观察者
         */
        super.notifyObservers(arg);
    }

    public static void main(String[] args) {
        Watcher watched = new Watcher();
        WatcherDemo watcherDemo = new WatcherDemo();
        watched.addObserver(watcherDemo);
        watched.addObserver(new Observer(){
            @Override
            public void update(Observable o, Object arg) {
                if(arg.toString().equals("closeWindows")){
                    System.out.println("已经关闭窗口");
                }
            }
        });
        //触发打开窗口事件，通知观察者
        watched.notifyObservers("openWindows");
        //触发关闭窗口事件，通知观察者
        watched.notifyObservers("closeWindows");
    }
}
