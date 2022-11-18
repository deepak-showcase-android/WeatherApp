package com.deepakbarad.weatherapp.data.interfaces

import com.deepakbarad.weatherapp.model.CurrentWeather

interface IOpenWeatherDataSource {
    suspend fun getForecast5(longitude: Double, latitude: Double): CurrentWeather
}