package com.udihaguel.weatherapp.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.udihaguel.weatherapp.db.DB_NAME
import com.udihaguel.weatherapp.db.WeatherDB
import com.udihaguel.weatherapp.db.dao.WeatherDao
import com.udihaguel.weatherapp.location.SharedLocationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Configuration for DI on the repository and shared location manager
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Provides
    @Singleton
    fun provideSharedLocationManager(
        @ApplicationContext context: Context,
        coroutineScope: CoroutineScope
    ): SharedLocationManager =
        SharedLocationManager(context,coroutineScope)

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager? =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?


    @Provides
    @Singleton
    fun provideDatabase(app: Application): WeatherDB =
        Room.databaseBuilder(app, WeatherDB::class.java, DB_NAME)
            .build()

}

