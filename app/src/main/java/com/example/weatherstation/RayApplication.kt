package com.example.weatherstation

import android.app.Application
import androidx.room.Room
import com.example.weatherstation.localdb.ItemDao
import com.example.weatherstation.localdb.ItemDatabase
import kotlin.properties.Delegates

class RayApplication :Application() {

    lateinit var dao: ItemDao

    companion object {
        var instance: RayApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        dao = Room.databaseBuilder(this, ItemDatabase::class.java, "records")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
            .dao()
    }
}