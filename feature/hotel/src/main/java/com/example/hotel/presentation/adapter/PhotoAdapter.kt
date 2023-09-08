package com.example.hotel.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotel.databinding.PhotoItemBinding

class PhotoAdapter(private val photoUrls: List<String>) : RecyclerView.Adapter<PhotoAdapter.PagerVH>() {
    class PagerVH(private val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photoUrl: String) {
            Glide.with(binding.root).load(photoUrl).into(binding.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoItemBinding.inflate(inflater, parent, false)
        return PagerVH(binding)
    }

    override fun getItemCount(): Int {
        return photoUrls.size
    }

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        holder.bind(photoUrls[position])
    }
}