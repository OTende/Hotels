package com.example.room.di

import com.example.room.data.repository.RoomRepositoryImpl
import com.example.room.domain.repository.RoomRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRoomRepository(roomRepository: RoomRepositoryImpl): RoomRepository
}