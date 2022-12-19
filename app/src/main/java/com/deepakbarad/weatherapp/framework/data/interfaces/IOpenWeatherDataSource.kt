package com.deepakbarad.weatherapp.framework.data.interfaces

import com.deepakbarad.weatherapp.framework.model.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface IOpenWeatherDataSource {
    suspend fun getForecast5(longitude: Double, latitude: Double): Flow<CurrentWeather>
}