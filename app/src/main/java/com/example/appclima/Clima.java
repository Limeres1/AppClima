package com.example.appclima;

public class Clima {
    private String ciudad;
    private double temperatura;
    private String descripcion;

    public Clima (String ciudad, double temperatura, String descripcion){
        this.ciudad = ciudad;
        this.temperatura = temperatura;
        this.descripcion = descripcion;
    }
    public String getCiudad(){
        return ciudad;
    }
    public double getTemperatura(){
        return temperatura;
    }
    public String getDescripcion(){
        return descripcion;
    }
}
