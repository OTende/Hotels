package com.example.room.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.domain.util.Resource
import com.example.room.data.model.Room
import com.example.room.domain.repository.RoomRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomViewModel @Inject constructor(private val repository: RoomRepository) : ViewModel() {
    class Factory @Inject constructor(private val repository: RoomRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return RoomViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _rooms = MutableLiveData<Resource<List<Room>>>()
    val rooms: LiveData<Resource<List<Room>>> = _rooms

    fun updateRoomsList() {
        viewModelScope.launch {
            _rooms.postValue(repository.getRooms())
        }
    }
}