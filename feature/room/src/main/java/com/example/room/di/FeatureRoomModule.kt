package com.example.room.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class, NetworkModule::class])
class FeatureRoomModule {
}