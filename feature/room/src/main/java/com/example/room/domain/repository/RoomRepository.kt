package com.example.room.domain.repository

import com.example.core.domain.util.Resource
import com.example.room.data.model.Room

interface RoomRepository {
    suspend fun getRooms(): Resource<List<Room>>
}