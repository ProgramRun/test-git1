package com.zad.webservice.server;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8888/weather", new WeatherImpl());
        System.out.println("publish is OK");
    }
}
