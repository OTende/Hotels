package com.example.booking.data.repository

import com.example.booking.data.model.BookingInfo
import com.example.booking.data.network.BookingApi
import com.example.booking.domain.repository.BookingRepository
import com.example.core.domain.util.Resource
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(private val bookingApi: BookingApi) : BookingRepository {
    override suspend fun getBookingInfo(): Resource<BookingInfo> {
        return try {
            Resource.loading(bookingApi.getBookingInfo())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.message ?: "Ошибка загрузки", null)
        }
    }
}