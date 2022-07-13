package com.udihaguel.weatherapp.di

import com.udihaguel.weatherapp.db.RemoteDb
import com.udihaguel.weatherapp.db.RemoteDbImpl
import com.udihaguel.weatherapp.network.RemoteApi
import com.udihaguel.weatherapp.network.RemoteApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsModule {

    @Binds
    @Singleton
    abstract fun bindRemoteApi(remoteApiImpl: RemoteApiImpl): RemoteApi

    @Binds
    @Singleton
    abstract fun bindRemoteDb(remoteDbImpl: RemoteDbImpl): RemoteDb
}