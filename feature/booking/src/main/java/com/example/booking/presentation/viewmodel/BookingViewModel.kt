package com.example.booking.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.booking.data.model.BookingInfo
import com.example.booking.domain.model.Tourist
import com.example.booking.domain.repository.BookingRepository
import com.example.core.domain.util.Resource
import com.example.core.domain.util.Status
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookingViewModel @Inject constructor(private val repository: BookingRepository) :
    ViewModel() {
    class Factory @Inject constructor(private val repository: BookingRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BookingViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BookingViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _bookingInfo = MutableLiveData<Resource<BookingInfo>>()
    val bookingInfo: LiveData<Resource<BookingInfo>> = _bookingInfo

    fun updateBookingInfo() {
        viewModelScope.launch {
            val result = async { repository.getBookingInfo() }
            _bookingInfo.postValue(result.await().copy(status = Status.SUCCESS))
//            _bookingInfo.postValue(repository.getBookingInfo())
        }
    }

    private val _tourists = MutableLiveData<MutableList<Tourist>>()
    val tourists: MutableLiveData<MutableList<Tourist>> = _tourists

    init {
        _tourists.postValue(mutableListOf(Tourist.getEmptyTourist()))
    }

    fun addTourist() {
        _tourists.value?.add(Tourist.getEmptyTourist())
    }
}