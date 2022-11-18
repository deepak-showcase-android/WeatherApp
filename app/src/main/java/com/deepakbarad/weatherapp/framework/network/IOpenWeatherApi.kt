package com.deepakbarad.weatherapp.framework.network

import com.deepakbarad.weatherapp.framework.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IOpenWeatherApi {
    @GET("/data/2.5/forecast")
    suspend fun getForecast5(@QueryMap options: Map<String, String>) : Response<CurrentWeather>
}