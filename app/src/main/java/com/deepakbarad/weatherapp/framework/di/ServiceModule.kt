package com.deepakbarad.weatherapp.framework.di

import android.location.LocationListener
import com.deepakbarad.weatherapp.framework.services.ILocationService
import com.deepakbarad.weatherapp.framework.services.LocationListenerService
import com.deepakbarad.weatherapp.framework.services.LocationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
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

    @Qualifier
    annotation class LocationServiceQualifier

    @Qualifier
    annotation class LocationListenerQualifier

}