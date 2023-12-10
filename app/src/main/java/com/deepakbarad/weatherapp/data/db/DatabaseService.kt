package com.deepakbarad.weatherapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deepakbarad.weatherapp.data.dao.WeatherDao
import com.deepakbarad.weatherapp.data.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
abstract class DatabaseService : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "Weather.db"
        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration().build()

        fun getInstance(context: Context): DatabaseService = instance ?: create(context).also {
            instance = it
        }
    }

    abstract fun weatherDao(): WeatherDao
}