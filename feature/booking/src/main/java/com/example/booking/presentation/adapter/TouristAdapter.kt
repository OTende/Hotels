package com.example.booking.presentation.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.booking.R
import com.example.booking.databinding.TouristItemBinding
import com.example.booking.domain.model.Tourist
import com.example.booking.domain.util.TouristCounter

class TouristAdapter(private val resources: Resources, private val onAddClickListener: () -> Unit) :
    ListAdapter<Tourist, TouristAdapter.TouristViewHolder>(TouristDiffUtil()) {

    class TouristViewHolder(
        private val binding: TouristItemBinding,
        private val resources: Resources,
        private val onClick: () -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: List<Tourist>, index: Int) {
            binding.addTourist.setOnClickListener {
                onClick.invoke()
                it.visibility = View.GONE
                binding.collide.visibility = View.VISIBLE
            }

            binding.collide.setOnClickListener {
                TransitionManager.beginDelayedTransition(
                    binding.collidableContainer,
                    AutoTransition()
                )
                binding.collidableContainer.isVisible = !binding.collidableContainer.isVisible
            }

            with(binding) {
                val item = list[index]
                name.setText(item.name)
                lastName.setText(item.name)
                birthday.setText(item.birthday)
                citizenship.setText(item.citizenship)
                foreignPassportNumber.setText(item.foreignPassportNumber)
                foreignPassportDate.setText(item.foreignPassportDate)
                nTourist.text =
                    resources.getString(R.string.n_tourist, TouristCounter.getStringById(index))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TouristItemBinding.inflate(inflater, parent, false)
        return TouristViewHolder(binding, resources, onAddClickListener)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        holder.bind(currentList, position)
    }
}