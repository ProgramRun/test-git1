package com.zad.jdk8.callback;

import java.util.Observable;
import java.util.Observer;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-14 15:51
 */
public class WatcherDemo implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        if(arg.toString().equals("openWindows")){
            System.out.println("已经打开窗口");
        }
    }
}
