package com.example.booking.di

import com.example.booking.presentation.fragment.BookingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FeatureHotelModule::class])
interface FeatureHotelComponent {
    fun inject(bookingFragment: BookingFragment)
}