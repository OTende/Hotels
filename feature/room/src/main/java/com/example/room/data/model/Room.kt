package com.example.room.data.model

import com.google.gson.annotations.SerializedName

data class Room(
    val id: Int,
    val name: String,
    val price: Int,
    @SerializedName("price_per")
    val priceInfo: String,
    val peculiarities: List<String>,
    @SerializedName("image_urls")
    val imageUrls: List<String>
)