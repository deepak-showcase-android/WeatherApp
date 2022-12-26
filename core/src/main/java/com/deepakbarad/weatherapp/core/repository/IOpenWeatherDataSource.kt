package com.deepakbarad.weatherapp.core.repository

import com.deepakbarad.weatherapp.core.data.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface IOpenWeatherDataSource {
    suspend fun getForecast5(longitude: Double, latitude: Double): Flow<CurrentWeather>
    suspend fun saveForecast(currentWeather: CurrentWeather)
    suspend fun getCachedForecast(): CurrentWeather?
}