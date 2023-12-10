package com.deepakbarad.weatherapp.domain.di

import com.deepakbarad.weatherapp.data.repository.OpenWeatherRepository
import com.deepakbarad.weatherapp.domain.usecase.GetCachedCurrentWeather
import com.deepakbarad.weatherapp.domain.usecase.GetWeather
import com.deepakbarad.weatherapp.domain.usecase.SaveWeather
import com.deepakbarad.weatherapp.domain.usecase.UseCases
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