package com.deepakbarad.weatherapp.framework.datasources.local

import android.content.Context
import com.deepakbarad.weatherapp.core.data.CurrentWeather
import com.deepakbarad.weatherapp.core.repository.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.framework.db.DatabaseService
import com.deepakbarad.weatherapp.framework.db.WeatherEntity
import kotlinx.coroutines.flow.Flow

class RoomWeatherDataSource(context: Context) : IOpenWeatherDataSource {

    val weatherDao = DatabaseService.getInstance(context).weatherDao()

    override suspend fun getForecast5(longitude: Double, latitude: Double): Flow<CurrentWeather> {
        throw IllegalArgumentException("not to be implemented")
    }

    override suspend fun saveForecast(currentWeather: CurrentWeather) {
        weatherDao.addWeatherEntity(WeatherEntity.fromCurrentWeather(currentWeather))
    }

    override suspend fun getCachedForecast() = weatherDao.getWeatherEntity()?.toCurrentWeather()
}