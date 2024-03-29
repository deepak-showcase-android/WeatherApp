package com.deepakbarad.weatherapp.core.services.di

import android.location.LocationListener
import com.deepakbarad.weatherapp.core.di.LocationListenerQualifier
import com.deepakbarad.weatherapp.core.di.LocationServiceQualifier
import com.deepakbarad.weatherapp.core.services.ILocationService
import com.deepakbarad.weatherapp.core.services.LocationListenerService
import com.deepakbarad.weatherapp.core.services.LocationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

//    @Provides
//    @Singleton
//    fun provideLocationService(@ApplicationContext appContext: Context): ILocationService =
//        LocationService(appContext, provideLocationListener())

//    @Provides
//    fun provideLocationListener(): LocationListenerService {
//        return LocationListenerService()
//    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class LocationServiceModule {
        @LocationServiceQualifier
        @Singleton
        @Binds
        abstract fun bindLocationService(impl: LocationService): ILocationService
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class LocationListenerModule {
        @LocationListenerQualifier
        @Singleton
        @Binds
        abstract fun bindLocationListener(impl: LocationListenerService): LocationListener
    }
}