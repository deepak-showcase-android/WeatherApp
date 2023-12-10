package com.deepakbarad.weatherapp.data.di

import android.app.Application
import com.deepakbarad.weatherapp.data.di.DataSourceModule
import com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideOpenWeatherRepository(app: Application) =
        OpenWeatherRepository(
            DataSourceModule.provideOpenWeatherRemoteDataSource(app),
            DataSourceModule.provideOpenWeatherLocalDataSource(app)
        )
}