package com.example.hotel.data.model

import com.google.gson.annotations.SerializedName

data class Hotel(
    val id: Int,
    val name: String,
    @SerializedName("adress")
    val address: String,
    @SerializedName("minimal_price")
    val minimalPrice: Int,
    @SerializedName("price_for_it")
    val priceDescription: String,
    val rating: Byte,
    @SerializedName("rating_name")
    val ratingName: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("about_the_hotel")
    val hotelAbout: HotelAbout
)