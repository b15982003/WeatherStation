package com.example.weatherstation

import android.app.Application
import kotlin.properties.Delegates

class RayApplication :Application() {
    // Depends on the flavor,
//    val stylishRepository: StylishRepository
//        get() = ServiceLocator.provideTasksRepository(this)

    companion object {
        var instance: RayApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}