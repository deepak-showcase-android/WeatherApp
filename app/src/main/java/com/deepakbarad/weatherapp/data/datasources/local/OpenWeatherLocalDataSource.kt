package com.deepakbarad.weatherapp.data.datasources.local

import android.content.Context
import com.deepakbarad.weatherapp.domain.model.CurrentWeather
import com.deepakbarad.weatherapp.data.repository.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.data.db.DatabaseService
import com.deepakbarad.weatherapp.data.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OpenWeatherLocalDataSource(context: Context) : IOpenWeatherDataSource {

    val weatherDao = DatabaseService.getInstance(context).weatherDao()

    override suspend fun getForecast5(longitude: Double, latitude: Double): Flow<CurrentWeather> {
        throw IllegalArgumentException("not to be implemented")
    }

    override suspend fun saveForecast(currentWeather: CurrentWeather) {
        val weatherEntity = WeatherEntity.fromCurrentWeather(currentWeather)
        println("Update -> saveForecast has id ${weatherEntity.id}")
        weatherDao.addWeatherEntity(weatherEntity)
    }

    override suspend fun getCachedForecast() = weatherDao.getWeatherEntity()?.toCurrentWeather()

    override suspend fun getCachedForecastFlow(): Flow<CurrentWeather?> {
        return weatherDao.getCachedForecastFlow().map { it?.toCurrentWeather() }
    }
}