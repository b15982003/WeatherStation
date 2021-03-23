package com.example.weatherstation

import android.app.Application
import kotlin.properties.Delegates

class RayApplication :Application() {

    companion object {
        var instance: RayApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}