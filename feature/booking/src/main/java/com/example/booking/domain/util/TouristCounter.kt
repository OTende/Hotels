package com.example.booking.domain.util

// Это надо было вынести в ресурсы
object TouristCounter {
    fun getStringById(id: Int): String {
        val numbers = listOf("Первый", "Второй", "Третий", "Четвёртый", "Пятый")
        return numbers[id]
    }
}