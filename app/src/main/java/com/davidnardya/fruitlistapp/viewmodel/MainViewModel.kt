package com.davidnardya.fruitlistapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.davidnardya.fruitlistapp.api.RetrofitInstance
import com.davidnardya.fruitlistapp.api.SimpleApi
import com.davidnardya.fruitlistapp.db.FruitDataBase
import com.davidnardya.fruitlistapp.model.FruitItem
import com.davidnardya.fruitlistapp.repositories.FruitRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    //Properties
    var apiService: SimpleApi = RetrofitInstance.api
    var fruitList: MutableLiveData<List<FruitItem>> = MutableLiveData()
    private val repository: FruitRepository


    init {
        val fruitDao = FruitDataBase.getDataBase(application).fruitDao()
        repository = FruitRepository(fruitDao)
        fruitList.value = repository.readAllData.value
    }

    fun getFruits() {
        viewModelScope.launch {
            fruitList.value = apiService.getFruits().fruits
            if (fruitList.value != null) {
                fruitList.value!!.forEach { fruit ->
                    repository.addFruit(fruit)
                }
            }
        }
    }
}