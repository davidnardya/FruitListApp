package com.davidnardya.fruitlistapp.repositories

import androidx.lifecycle.LiveData
import com.davidnardya.fruitlistapp.dao.FruitDao
import com.davidnardya.fruitlistapp.model.FruitItem

class FruitRepository(private val fruitDao: FruitDao) {

    val readAllData: LiveData<List<FruitItem>> = fruitDao.readAllData()

    suspend fun addFruit(fruitItem: FruitItem){
        fruitDao.addFruit(fruitItem)
    }

}