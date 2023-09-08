package com.example.booking.di

import com.example.booking.data.repository.BookingRepositoryImpl
import com.example.booking.domain.repository.BookingRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindBookingRepository(bookingRepositoryImpl: BookingRepositoryImpl): BookingRepository
}