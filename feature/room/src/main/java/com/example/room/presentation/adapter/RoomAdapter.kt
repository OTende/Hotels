package com.example.room.presentation.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.room.R
import com.example.room.data.model.Room
import com.example.room.databinding.RoomItemBinding

const val BOOKING_URI = "android-app:/booking/"

class RoomAdapter(private val rooms: List<Room>, private val resources: Resources) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    class RoomViewHolder(private val binding: RoomItemBinding, private val resources: Resources) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(room: Room) {
                with (binding) {
                    name.text = room.name
                    photos.adapter = RoomPhotoAdapter(room.imageUrls)
                    peculiarities.adapter = PeculiarityAdapter(room.peculiarities)
                    priceInfo.text = room.priceInfo
                    price.text = resources.getString(R.string.price, room.price)
                    val adapter = PeculiarityAdapter(room.peculiarities)
                    binding.peculiarities.apply {
                        this.adapter = adapter
                        layoutManager = StaggeredGridLayoutManager(room.peculiarities.size - 1, StaggeredGridLayoutManager.VERTICAL)
                    }
                    pickARoom.setOnClickListener {
                        val request = NavDeepLinkRequest.Builder
                            .fromUri(BOOKING_URI.toUri())
                            .build()
                        it.findNavController().navigate(request)
//                        findNavController().navigate(request)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoomItemBinding.inflate(inflater, parent, false)
        return RoomViewHolder(binding, resources)
    }

    override fun getItemCount(): Int {
        return rooms.size
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(rooms[position])
    }
}