package com.example.weatherstation.localdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherstation.data.Records

@Dao
abstract class ItemDao {

    @Query("SELECT * FROM records")
    abstract fun query(): LiveData<List<Records>>

    @Insert
    abstract fun insert(records: Records)

    @Query("delete from Records where siteId = :id")
    abstract fun deleteById(id: String)

    @Query("delete from Records")
    abstract fun deleteAll()

}