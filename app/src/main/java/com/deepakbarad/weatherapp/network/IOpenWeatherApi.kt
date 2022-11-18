package com.deepakbarad.weatherapp.network

import com.deepakbarad.weatherapp.model.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IOpenWeatherApi {
    @GET("/data/2.5/forecast")
    suspend fun getForecast5(@QueryMap options: Map<String, String>) : Response<CurrentWeather>

    //http://api.openweathermap.org/data/2.5/forecast?lat=12.972442&lon=77.580643&appid=d242ceb4ca60d98d2385dc641f549b22
}