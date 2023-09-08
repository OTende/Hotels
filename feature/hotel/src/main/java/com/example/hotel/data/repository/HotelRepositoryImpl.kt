package com.example.hotel.data.repository

import com.example.core.domain.util.Resource
import com.example.hotel.data.model.Hotel
import com.example.hotel.data.network.HotelApi
import com.example.hotel.domain.repository.HotelRepository
import javax.inject.Inject

class HotelRepositoryImpl @Inject constructor(private val hotelApi: HotelApi) : HotelRepository {
    override suspend fun getHotelInfo(): Resource<Hotel> {
        return try {
            Resource.loading(hotelApi.getHotel())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.message ?: "Ошибка загрузки", null)
        }
    }
}