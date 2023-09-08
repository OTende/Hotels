package com.example.hotel.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.domain.util.Resource
import com.example.core.domain.util.Status
import com.example.hotel.data.model.Hotel
import com.example.hotel.domain.repository.HotelRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HotelViewModel @Inject constructor(private val repository: HotelRepository) : ViewModel() {
    class Factory @Inject constructor(private val repository: HotelRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HotelViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HotelViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _hotel = MutableLiveData<Resource<Hotel>>()
    val hotel: LiveData<Resource<Hotel>> = _hotel

    fun updateHotel() {
        viewModelScope.launch {
            val result = async { repository.getHotelInfo() }
            _hotel.postValue(result.await().copy(status = Status.SUCCESS))
        }
    }
}