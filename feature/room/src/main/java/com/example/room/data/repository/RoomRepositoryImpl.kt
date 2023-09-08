package com.example.room.data.repository

import com.example.core.domain.util.Resource
import com.example.room.data.model.Room
import com.example.room.data.network.RoomApi
import com.example.room.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val roomApi: RoomApi): RoomRepository {
    override suspend fun getRooms(): Resource<List<Room>> {
        return try {
            Resource.success(roomApi.getRooms().rooms)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error(e.message ?: "Ошибка загрузки", null)
        }
    }
}