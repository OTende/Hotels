package com.example.hotel.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class, NetworkModule::class])
class FeatureHotelModule(
    val application: Application
) {
    @Provides
    @Singleton
    fun application(): Application = application

}