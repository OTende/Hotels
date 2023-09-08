package com.example.room.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.room.databinding.RoomPhotoItemBinding

class RoomPhotoAdapter(private val photoUrls: List<String>) : RecyclerView.Adapter<RoomPhotoAdapter.PhotoVH>() {
    class PhotoVH(private val binding: RoomPhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photoUrl: String) {
            Glide.with(binding.root).load(photoUrl).into(binding.photo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoomPhotoItemBinding.inflate(inflater, parent, false)
        return PhotoVH(binding)
    }

    override fun getItemCount(): Int {
        return photoUrls.size
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {
        holder.bind(photoUrls[position])
    }
}