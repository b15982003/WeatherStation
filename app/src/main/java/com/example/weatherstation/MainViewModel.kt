package com.example.weatherstation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherstation.data.WeatherData
import com.example.weatherstation.network.RetrofitApi
import com.example.weatherstation.util.UtilLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val _weatherData = MutableLiveData<WeatherData>()
    val weatherData: LiveData<WeatherData>
        get() = _weatherData

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getWeatherStatus()
        UtilLog.d("here")

    }

    fun getWeatherStatus() {
        coroutineScope.launch {
            try {
                val response = RetrofitApi.retrofitService.getWeatherStatus()
                if (response.isSuccessful) {
                    _weatherData.value = response.body()
                }

            } catch (e: Exception) {
                _weatherData.value = null
            }
        }
    }
}