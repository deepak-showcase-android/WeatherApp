package com.deepakbarad.weatherapp.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.deepakbarad.weatherapp.core.data.CurrentWeather
import com.google.gson.Gson

@Entity(tableName = "WeatherData")
data class WeatherEntity(
    val weatherData: String,
    val createdTime: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1
) {

    fun toCurrentWeather(): CurrentWeather {
        val currentWeather: CurrentWeather =
            Gson().fromJson(weatherData, CurrentWeather::class.java)
        currentWeather.id = id
        return currentWeather
    }

    companion object {
        fun fromCurrentWeather(currentWeather: CurrentWeather): WeatherEntity {
            return WeatherEntity(
                Gson().toJson(currentWeather),
                System.currentTimeMillis()
            ).also {
                it.id = currentWeather.id
            }
        }
    }
}