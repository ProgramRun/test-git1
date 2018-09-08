package com.zad.webservice.server;

import javax.jws.WebService;

@WebService
public class WeatherImpl implements WeatherInterface {

    public String weather() {
        return "sun over there";
    }
}
