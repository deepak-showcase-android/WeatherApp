package com.deepakbarad.weatherapp.di

import com.deepakbarad.weatherapp.core.network.IOpenWeatherApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestNetworkModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class OpenWeatherApiModule {
        @OpenWeatherApiQualifier
        @Singleton
        @Binds
        abstract fun bindOpenWeatherApi(impl: IOpenWeatherApi): IOpenWeatherApi
    }

    @Qualifier
    annotation class OpenWeatherApiQualifier
}