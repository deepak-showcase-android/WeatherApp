package com.deepakbarad.weatherapp.network

import com.deepakbarad.weatherapp.core.data.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IOpenWeatherApi {
    @GET("/data/2.5/forecast")
    suspend fun getForecast5(@QueryMap options: Map<String, String>): Response<CurrentWeather>
}