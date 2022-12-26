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
    val id: Int = 0
) {

    fun toCurrentWeather(): CurrentWeather {
        val currentWeather: CurrentWeather =
            Gson().fromJson<CurrentWeather>(weatherData, CurrentWeather::class.java)
        currentWeather.id = id
        return currentWeather
    }

    companion object {
        fun fromCurrentWeather(currentWeather: CurrentWeather): WeatherEntity {
            return WeatherEntity(
                Gson().toJson(currentWeather),
                System.currentTimeMillis()
            )
        }
    }
}