package com.udihaguel.weatherapp.network

import android.net.ConnectivityManager
import android.util.Log
import com.udihaguel.weatherapp.model.*
import javax.inject.Inject

class RemoteApiImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val networkStatusChecker: NetworkStatusChecker
) : RemoteApi {

    override suspend fun getWeatherByLatLng(lat: Double, lng: Double): WeatherResult<WeatherWrapper> = try {
        Success(weatherApiService.getWeatherByLatLng(lat, lng))
    } catch (e:Throwable){
        Log.e("Udi#Log", Failure(e).exc?.message.toString())
        Failure(e)
    }


}