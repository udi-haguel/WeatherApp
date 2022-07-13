package com.udihaguel.weatherapp.network

import com.udihaguel.weatherapp.model.WeatherResult
import com.udihaguel.weatherapp.model.WeatherWrapper

interface RemoteApi {

    suspend fun getWeatherByLatLng(lat:Double,lng:Double): WeatherResult<WeatherWrapper>

}
