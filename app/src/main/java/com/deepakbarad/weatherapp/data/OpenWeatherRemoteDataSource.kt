package com.deepakbarad.weatherapp.data

import com.deepakbarad.weatherapp.BuildConfig
import com.deepakbarad.weatherapp.data.interfaces.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.di.NetworkModule
import com.deepakbarad.weatherapp.model.CurrentWeather
import com.deepakbarad.weatherapp.network.IOpenWeatherApi
import javax.inject.Inject

class OpenWeatherRemoteDataSource @Inject constructor(
    @NetworkModule.OpenWeatherApiQualifier private val openWeatherApi: IOpenWeatherApi
) : IOpenWeatherDataSource {

    override suspend fun getForecast5(longitude: Double, latitude: Double): CurrentWeather {
        val queryMap: MutableMap<String, String> = mutableMapOf()
        queryMap["lat"] = latitude.toString()
        queryMap["lon"] = longitude.toString()
        queryMap["appid"] = "d242ceb4ca60d98d2385dc641f549b22"
        val currentWeather = openWeatherApi.getForecast5(queryMap)
        println(currentWeather.body()?.message)
        return currentWeather.body() ?: throw Exception("No current weather info available")
    }
}