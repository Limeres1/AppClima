package com.example.appclima.modelo;

import java.util.List;

public class RespuestaClima {

    private String name;
    private Main main;
    private List<Weather> weather;

    public String getName() {
        return name;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}