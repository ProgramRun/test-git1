package com.zad.jdk8.callback;

/**
 * 描述:
 *
 * @author zad
 * @create 2019-03-14 14:58
 */
public class Caller {
    public void call(ICallBack callBack) {
        System.out.println("start...");
        callBack.callBack();
        System.out.println("end...");
    }

    public static void main(String[] args) {
        Caller c = new Caller();

        c.call(new ICallBack() {
            @Override
            public void callBack() {
                System.out.println("hero's come back");
            }
        });
    }
}
