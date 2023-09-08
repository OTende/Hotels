package com.example.booking.domain.repository

import com.example.booking.data.model.BookingInfo
import com.example.core.domain.util.Resource

interface BookingRepository {
    suspend fun getBookingInfo(): Resource<BookingInfo>
}