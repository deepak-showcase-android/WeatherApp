package com.deepakbarad.weatherapp.framework.di

import android.app.Application
import com.deepakbarad.weatherapp.core.repository.OpenWeatherRepository
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