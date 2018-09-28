package com.zad.servlet;

import com.zad.http.MyRequest;
import com.zad.http.MyResponse;
import com.zad.http.ZadServlet;

import java.io.UnsupportedEncodingException;

/**
 * 描述:
 * as
 *
 * @author zad
 * @create 2018-09-24 7:37
 */
public class MyServlet extends ZadServlet {

    @Override
    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write(request.getParameter("name"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) {

    }
}
