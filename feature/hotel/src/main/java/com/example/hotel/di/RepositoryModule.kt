package com.example.hotel.di

import com.example.hotel.data.repository.HotelRepositoryImpl
import com.example.hotel.domain.repository.HotelRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHotelRepository(hotelRepositoryImpl: HotelRepositoryImpl): HotelRepository
}