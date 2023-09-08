package com.example.booking.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booking.databinding.TouristItemBinding
import com.example.booking.domain.model.Tourist


class TouristAdapter :
    ListAdapter<Tourist, TouristAdapter.TouristViewHolder>(TouristDiffUtil()) {
    
    class TouristViewHolder(private val binding: TouristItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Tourist) {
            binding.name.setText(item.name)
            binding.lastName.setText(item.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TouristItemBinding.inflate(inflater, parent, false)
        return TouristViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}