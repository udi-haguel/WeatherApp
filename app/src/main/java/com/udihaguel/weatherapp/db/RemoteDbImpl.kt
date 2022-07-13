package com.udihaguel.weatherapp.db

import com.udihaguel.weatherapp.db.dao.WeatherDao
import com.udihaguel.weatherapp.model.Success
import com.udihaguel.weatherapp.model.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDbImpl @Inject constructor(
    private val weatherDao: WeatherDao
) : RemoteDb {

    override suspend fun updateLatestLocation(weather: WeatherData) {
        weatherDao.updateLatestLocation(weather)
    }

    override fun getLatestLocation(id: String): Flow<WeatherData> = weatherDao.getLatestLocation(id)
}