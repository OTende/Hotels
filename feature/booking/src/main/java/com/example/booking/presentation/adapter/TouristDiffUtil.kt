package com.example.booking.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.booking.domain.model.Tourist

class TouristDiffUtil : DiffUtil.ItemCallback<Tourist>() {
    override fun areItemsTheSame(oldItem: Tourist, newItem: Tourist): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Tourist, newItem: Tourist): Boolean {
        return oldItem.foreignPassportNumber == newItem.foreignPassportNumber
    }
}