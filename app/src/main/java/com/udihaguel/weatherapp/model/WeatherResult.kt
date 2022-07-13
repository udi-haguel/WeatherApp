package com.udihaguel.weatherapp.model

sealed class WeatherResult<out T: Any>

data class Success<out T:Any>(val data: T): WeatherResult<T>()
data class Failure(val exc: Throwable?): WeatherResult<Nothing>()
data class NoInternet(val message:String): WeatherResult<Nothing>()