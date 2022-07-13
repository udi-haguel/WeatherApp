package com.udihaguel.weatherapp.model

import com.google.gson.annotations.SerializedName

data class WeatherWrapper(
    @SerializedName("data")
    val weather: WeatherData,
    val message: String
)