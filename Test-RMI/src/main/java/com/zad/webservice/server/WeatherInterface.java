package com.zad.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WeatherInterface {
    @WebMethod
    String weather();
}
