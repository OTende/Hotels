package com.example.hotel.di

import com.example.hotel.presentation.fragment.HotelFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FeatureHotelModule::class])
interface FeatureHotelComponent {
//    @Component.Factory
//    interface Factory {
//        fun create(appComponent: AppComponent)
//    }
//
    fun inject(hotelFragment: HotelFragment)
}