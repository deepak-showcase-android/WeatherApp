package com.deepakbarad.weatherapp.framework.di

import android.content.Context
import com.deepakbarad.weatherapp.core.di.OpenWeatherRemoteDataSourceQualifier
import com.deepakbarad.weatherapp.core.repository.IOpenWeatherDataSource
import com.deepakbarad.weatherapp.framework.datasources.remote.OpenWeatherRemoteDataSource
import com.deepakbarad.weatherapp.framework.network.IOpenWeatherApi
import com.deepakbarad.weatherapp.framework.network.RetrofitHelper
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
}

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderRemoteDataSourceModule {
    @OpenWeatherRemoteDataSourceQualifier
    @Singleton
    @Binds
    abstract fun bindOpenWeatherRemoteDataSource(impl: OpenWeatherRemoteDataSource): IOpenWeatherDataSource
}