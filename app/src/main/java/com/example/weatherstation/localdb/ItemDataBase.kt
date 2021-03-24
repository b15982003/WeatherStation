package com.example.weatherstation.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherstation.data.Records

@Database(entities = [Records::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun dao(): ItemDao

}