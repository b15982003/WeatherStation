package com.example.weatherstation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherstation.data.Records
import com.example.weatherstation.data.WeatherData
import com.example.weatherstation.network.RetrofitApi
import com.example.weatherstation.util.UtilLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    val dao = RayApplication.instance.dao

    val items = dao.query()

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
                    for (i in 0..response.body()?.records!!.size) {
                        insertData(response.body()?.records!![i])
                    }
                }
            } catch (e: Exception) {
                UtilLog.d("response wrong$e")
                _weatherData.value = null
            }
        }
    }

    fun addItem(todo: Records) {
        dao.insert(todo)
    }

    fun deleteById(id: String) {
        dao.deleteById(id)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    private fun insertData(records: Records) {
        addItem(records)
    }
}