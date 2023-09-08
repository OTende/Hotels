package com.example.booking.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.R
import com.example.booking.databinding.FragmentBookingBinding
import com.example.booking.di.DaggerFeatureHotelComponent
import com.example.booking.domain.util.PhoneWatcher
import com.example.booking.domain.util.Validator
import com.example.booking.presentation.adapter.TouristAdapter
import com.example.booking.presentation.viewmodel.BookingViewModel
import com.example.core.domain.util.Status
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class BookingFragment : Fragment() {
    @Inject
    lateinit var viewModel: BookingViewModel

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFeatureHotelComponent.builder().build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        val adapter = TouristAdapter(resources) {
            viewModel.addTourist()
        }
        binding.touristsRv.adapter = adapter
        binding.touristsRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.tourists.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        val phoneWatcher =
            PhoneWatcher(binding.phoneNumber, resources.getColor(R.color.error_color, null))
        phoneWatcher.setWatcher()

        binding.paymentBtn.setOnClickListener {
            val isValidEmail = validateEditBox(binding.email) {
                Validator.isValidEmail(it)
            }
            val isValidPhone = validateEditBox(binding.phoneNumber) {
                Validator.isValidPhoneNumber(it)
            }
            if (adapter.validateAll() && isValidPhone && isValidEmail) {
                findNavController().navigate(R.id.action_bookingFragment_to_finishFragment)
            }
        }


        viewModel.updateBookingInfo()
        viewModel.bookingInfo.observe(viewLifecycleOwner) { value ->
            when (value.status) {
                Status.SUCCESS -> {
                    value.data?.let {
                        with(binding) {
                            hotelName.text = it.hotelName
                            address.text = it.hotelAddress
                            flightFromTv.text = it.departure
                            destinationTv.text = it.arrivalCountry
                            datesTv.text =
                                getString(R.string.dates_window, it.tourDateStart, it.tourDateStop)
                            nightsNumberTv.text = it.nightsNumber.toString()
                            hotelTv.text = it.hotelName
                            roomTv.text = it.room
                            foodTv.text = it.nutrition
                            hotelRating.text = resources.getString(
                                com.example.core.R.string.rating,
                                it.rating,
                                it.ratingName
                            )
                        }
                    }
                }

                Status.LOADING -> {

                }

                Status.ERROR -> {

                }
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun validateEditBox(editBox: TextInputEditText, verificationFun: (String) -> Boolean): Boolean {
        if (!verificationFun.invoke(editBox.text.toString())) {
            editBox.setBackgroundColor(resources.getColor(R.color.error_color, null))
            return false
        }
        return true
    }
}
