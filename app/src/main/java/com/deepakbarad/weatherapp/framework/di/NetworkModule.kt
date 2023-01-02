package com.deepakbarad.weatherapp.framework.di

import com.deepakbarad.weatherapp.core.di.OpenWeatherApiQualifier
import com.deepakbarad.weatherapp.framework.network.IOpenWeatherApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class OpenWeatherApiModule {
        @OpenWeatherApiQualifier
        @Singleton
        @Binds
        abstract fun bindOpenWeatherApi(impl: IOpenWeatherApi): IOpenWeatherApi
    }

}