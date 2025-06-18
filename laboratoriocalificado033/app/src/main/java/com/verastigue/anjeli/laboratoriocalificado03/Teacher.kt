package com.verastigue.anjeli.laboratoriocalificado03

import com.google.gson.annotations.SerializedName

data class Teacher(
    val name: String,
    @SerializedName("last_name")
    val lastName: String,
    val phone: String,
    val email: String,
    val imageUrl: String
)