package com.example.room.data.network

import com.example.room.data.model.Room
import retrofit2.http.GET

interface RoomApi {
    // Надеюсь, этого никто не увидит...
    data class Rooms(val rooms: List<Room>)

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getRooms(): Rooms
}