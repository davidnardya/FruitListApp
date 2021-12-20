package com.davidnardya.fruitlistapp.model


import com.google.gson.annotations.SerializedName

data class FruitList(
    @SerializedName("err")
    val err: Int,
    @SerializedName("fruits")
    val fruits: List<FruitItem>
)