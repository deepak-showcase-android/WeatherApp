package com.deepakbarad.weatherapp.framework.di

import com.deepakbarad.weatherapp.core.repository.OpenWeatherRepository
import com.deepakbarad.weatherapp.core.usecase.GetCachedCurrentWeather
import com.deepakbarad.weatherapp.core.usecase.GetWeather
import com.deepakbarad.weatherapp.core.usecase.SaveWeather
import com.deepakbarad.weatherapp.framework.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun provideUseCases(openWeatherRepository: OpenWeatherRepository) = UseCases(
        GetWeather(openWeatherRepository),
        SaveWeather(openWeatherRepository),
        GetCachedCurrentWeather(openWeatherRepository)
    )
}