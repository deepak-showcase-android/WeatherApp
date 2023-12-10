package com.deepakbarad.weatherapp.core.di

import javax.inject.Qualifier

@Qualifier
annotation class OpenWeatherRemoteDataSourceQualifier

@Qualifier
annotation class OpenWeatherLocalDataSourceQualifier

@Qualifier
annotation class OpenWeatherApiQualifier

@Qualifier
annotation class LocationServiceQualifier

@Qualifier
annotation class LocationListenerQualifier