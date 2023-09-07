package com.example.hotel.domain.repository

import com.example.core.domain.util.Resource
import com.example.hotel.data.model.Hotel

interface HotelRepository {
    suspend fun getHotelInfo(): Resource<Hotel>
}