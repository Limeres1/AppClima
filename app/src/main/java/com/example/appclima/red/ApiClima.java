package com.example.appclima.red;

import com.example.appclima.modelo.RespuestaClima;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClima {

    @GET("weather")
    Call<RespuestaClima> obtenerClima(
            @Query("q") String ciudad,
            @Query("appid") String apiKey,
            @Query("units") String unidades,
            @Query("lang") String idioma
    );
}