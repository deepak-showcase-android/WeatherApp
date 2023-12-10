package com.deepakbarad.weatherapp.core.network

import com.deepakbarad.weatherapp.domain.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IOpenWeatherApi {
    @GET("/data/2.5/forecast")
    suspend fun getForecast5(@QueryMap options: Map<String, String>) : Response<CurrentWeather>
}