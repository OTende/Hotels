package com.example.booking.domain.model

data class Tourist(
    val name: String?,
    val lastName: String?,
    val birthday: String?,
    val citizenship: String?,
    val foreignPassportNumber: String?,
    val foreignPassportDate: String?
) {
    companion object {
        fun getEmptyTourist(): Tourist = Tourist(
            null,
            null,
            null,
            null,
            null,
            null,
        )
    }
}