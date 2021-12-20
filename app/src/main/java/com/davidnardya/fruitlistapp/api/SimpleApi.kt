package com.davidnardya.fruitlistapp.api

import com.davidnardya.fruitlistapp.model.FruitList
import retrofit2.http.GET

interface SimpleApi {

    @GET("/fruitsBT/getFruits")
    suspend fun getFruits(
    ): FruitList

}