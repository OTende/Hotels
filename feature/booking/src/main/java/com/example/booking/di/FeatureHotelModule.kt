package com.example.booking.di

import dagger.Module

@Module(includes = [RepositoryModule::class, NetworkModule::class])
class FeatureHotelModule