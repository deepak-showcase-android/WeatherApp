package com.deepakbarad.weatherapp.di

import android.content.Context
import com.deepakbarad.weatherapp.services.LocationListener
import com.deepakbarad.weatherapp.services.LocationService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideLocationService(@ApplicationContext appContext: Context): LocationService =
        LocationService(appContext, provideLocationListener())

    @Provides
    fun provideLocationListener(): LocationListener {
        return LocationListener()
    }
}