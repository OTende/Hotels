package com.example.room.di

import com.example.room.presentation.fragment.RoomFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface FeatureRoomComponent {
    fun inject(roomFragment: RoomFragment)
}