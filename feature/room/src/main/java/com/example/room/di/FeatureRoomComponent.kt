package com.example.room.di

import com.example.room.presentation.fragment.RoomFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FeatureRoomModule::class])
interface FeatureRoomComponent {
    fun inject(roomFragment: RoomFragment)
}