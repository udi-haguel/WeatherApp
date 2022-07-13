package com.udihaguel.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "LatestLocation")
data class WeatherData(
    @PrimaryKey
    val id:String = "latest",
    val apparentTemperature: Double,
    val cloudCover: Int,
    val dewPoint: Double,
    val humidity: Double,
    val icon: String,
    val lat: Double,
    val lng: Double,
    val ozone: Double,
    val precipIntensity: Int,
    val pressure: Double,
    val summary: String,
    val temperature: Double,
    val time: Int,
    val uvIndex: Double,
    val visibility: Double,
    val windBearing: Double,
    val windGust: Double,
    val windSpeed: Double
){
    fun tempAsCelsius():String {
        val celsius = (temperature - 32) / 1.8
        return "%.2f".format(celsius) + "°C"
    }

    fun getIconUrl():String = "https://assetambee.s3-us-west-2.amazonaws.com/weatherIcons/PNG/$icon.png"
}