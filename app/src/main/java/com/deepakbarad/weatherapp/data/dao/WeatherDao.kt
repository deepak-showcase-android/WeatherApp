package com.deepakbarad.weatherapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.deepakbarad.weatherapp.data.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = REPLACE)
    suspend fun addWeatherEntity(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM WeatherData")
    suspend fun getWeatherEntity(): WeatherEntity?

    @Query("SELECT * FROM WeatherData")
    fun getCachedForecastFlow(): Flow<WeatherEntity?>
}