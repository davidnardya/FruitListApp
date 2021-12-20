package com.davidnardya.fruitlistapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.davidnardya.fruitlistapp.model.FruitItem

@Dao
interface FruitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFruit(fruitItem: FruitItem)

    @Query("SELECT * FROM fruit_table")
    fun readAllData(): LiveData<List<FruitItem>>
}