package com.zad.http;

/**
 * 描述:
 *
 * @author zad
 * @create 2018-09-24 7:33
 */
public abstract class ZadServlet {

    public abstract void doGet(MyRequest request,MyResponse response);

    public abstract  void doPost(MyRequest request,MyResponse response);
}
