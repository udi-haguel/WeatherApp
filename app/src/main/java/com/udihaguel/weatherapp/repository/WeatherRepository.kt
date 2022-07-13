package com.udihaguel.weatherapp.repository

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.udihaguel.weatherapp.db.RemoteDb
import com.udihaguel.weatherapp.model.*
import com.udihaguel.weatherapp.network.RemoteApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    //private val remoteDb: RemoteDb,
    private val remoteApi: RemoteApi
    ){


    fun fetchWeatherFromInternet(lat:Double, lng:Double) = flow<WeatherWrapper> {
        val data = remoteApi.getWeatherByLatLng(lat,lng)
        if (data is Success)
            emit(data.data)
        if (data is Failure)
            Log.e("Udi#Log", "fetching failed")
    }


    //fun fetchWeatherFromDb(lat: Double, lng: Double) = roomRepository.getLatestLocation("latest")

    fun getAddress(context: Context, lat: Double, lng: Double): String {
        val geocoder = Geocoder(context)
        val list = geocoder.getFromLocation(lat, lng, 1)
        return if (list.isNullOrEmpty() || list[0].getAddressLine(0).isNullOrEmpty()) {
            "Unknown Location"
        } else {
            list[0].getAddressLine(0)
        }
    }

}