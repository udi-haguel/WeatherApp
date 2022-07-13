package com.udihaguel.weatherapp.network

import com.udihaguel.weatherapp.model.WeatherWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather/latest/by-lat-lng")
    suspend fun getWeatherByLatLng(@Query("lat") lat:Double, @Query("lng") lng:Double): WeatherWrapper

}