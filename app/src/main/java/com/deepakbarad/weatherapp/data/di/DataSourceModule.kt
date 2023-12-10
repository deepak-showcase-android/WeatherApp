package com.deepakbarad.weatherapp.data.di

import android.content.Context
import com.deepakbarad.weatherapp.core.di.OpenWeatherLocalDataSourceQualifier
import com.deepakbarad.weatherapp.core.di.OpenWeatherRemoteDataSourceQualifier
import com.deepakbarad.weatherapp.data.repository.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.data.datasources.local.OpenWeatherLocalDataSource
import com.deepakbarad.weatherapp.data.datasources.remote.OpenWeatherRemoteDataSource
import com.deepakbarad.weatherapp.core.network.IOpenWeatherApi
import com.deepakbarad.weatherapp.core.network.RetrofitHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideOpenWeatherRemoteDataSource(@ApplicationContext appContext: Context): OpenWeatherRemoteDataSource =
        OpenWeatherRemoteDataSource(
            RetrofitHelper.getInstance().create(IOpenWeatherApi::class.java)
        )

    @Singleton
    @Provides
    fun provideOpenWeatherLocalDataSource(@ApplicationContext appContext: Context): OpenWeatherLocalDataSource =
        OpenWeatherLocalDataSource(appContext)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRemoteDataSourceModule {
    @OpenWeatherRemoteDataSourceQualifier
    @Singleton
    @Binds
    abstract fun bindOpenWeatherRemoteDataSource(impl: OpenWeatherRemoteDataSource): IOpenWeatherDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderLocalDataSourceModule {
    @OpenWeatherLocalDataSourceQualifier
    @Singleton
    @Binds
    abstract fun bindOpenWeatherLocalDataSource(impl: OpenWeatherLocalDataSource): IOpenWeatherDataSource
}