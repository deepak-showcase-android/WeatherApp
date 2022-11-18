package com.deepakbarad.weatherapp.di

import com.deepakbarad.weatherapp.data.OpenWeatherRemoteDataSource
import com.deepakbarad.weatherapp.data.interfaces.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.network.IOpenWeatherApi
import com.deepakbarad.weatherapp.network.RetrofitHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
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

    @Qualifier
    annotation class OpenWeatherApiQualifier
}