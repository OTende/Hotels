package com.example.hotel.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hotel.databinding.PeculiarityItemBinding

class PeculiarityAdapter(private val peculiarities: List<String>) :
    RecyclerView.Adapter<PeculiarityAdapter.PeculiarityViewHolder>() {
    class PeculiarityViewHolder(private val binding: PeculiarityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.peculiarity.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeculiarityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeculiarityItemBinding.inflate(inflater, parent, false)
        return PeculiarityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeculiarityViewHolder, position: Int) {
        holder.bind(peculiarities[position])
    }

    override fun getItemCount(): Int {
        return peculiarities.size
    }
}