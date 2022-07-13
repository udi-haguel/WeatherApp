package com.udihaguel.weatherapp.ui.main

import android.content.Context
import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udihaguel.weatherapp.model.WeatherData
import com.udihaguel.weatherapp.repository.RoomRepository
import com.udihaguel.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    val weatherDataStateFlow: MutableSharedFlow<WeatherData> = MutableSharedFlow(0)


    fun fetchData(lat:Double, lng:Double){
        viewModelScope.launch {
            weatherRepository.fetchWeatherFromInternet(lat,lng).collect{
                weatherDataStateFlow.emit(it.weather)
            }
        }
    }

    fun getAddress(context: Context, lat: Double, lng: Double): String =
        weatherRepository.getAddress(context, lat, lng)

}