package com.example.booking.presentation.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.booking.R
import com.example.booking.databinding.TouristItemBinding
import com.example.booking.domain.model.Tourist
import com.example.booking.domain.util.TouristCounter
import com.google.android.material.textfield.TextInputEditText

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

        private fun validate(view: TextInputEditText): Boolean {
            if (view.text.isNullOrEmpty()) {
                view.error = resources.getString(R.string.fill_data)
                view.setBackgroundColor(resources.getColor(R.color.error_color, resources.newTheme()))
                return false
            }
            return true
        }

        // Это одна из самых страшных вещей, что я писал
        fun validateAll(): Boolean {
            val validationList = mutableListOf<Boolean>()
            validationList.addAll(
                listOf(
                    validate(binding.name),
                    validate(binding.lastName),
                    validate(binding.birthday),
                    validate(binding.citizenship),
                    validate(binding.foreignPassportDate),
                    validate(binding.foreignPassportNumber)
                )
            )
            return !validationList.contains(false)
        }
    }

    private var holdersToValidate: MutableList<TouristViewHolder> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TouristItemBinding.inflate(inflater, parent, false)
        val viewHolder = TouristViewHolder(binding, resources, onAddClickListener)
        holdersToValidate.add(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        holder.bind(currentList, position)
    }

    fun validateAll(): Boolean {
        val validationList = mutableListOf<Boolean>()
        holdersToValidate.forEach {
            validationList.add(it.validateAll())
        }
        return !validationList.contains(false)
    }
}