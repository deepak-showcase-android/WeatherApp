package com.deepakbarad.weatherapp.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
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