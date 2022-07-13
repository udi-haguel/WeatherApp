package com.udihaguel.weatherapp.db

import com.udihaguel.weatherapp.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface RemoteDb {

    suspend fun updateLatestLocation(weather: WeatherData)
    fun getLatestLocation(id:String): Flow<WeatherData>

}