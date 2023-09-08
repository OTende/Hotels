package com.example.booking.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booking.R
import com.example.booking.databinding.FragmentBookingBinding
import com.example.booking.di.DaggerFeatureHotelComponent
import com.example.booking.domain.util.PhoneWatcher
import com.example.booking.presentation.adapter.TouristAdapter
import com.example.booking.presentation.viewmodel.BookingViewModel
import com.example.core.domain.util.Status
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

        binding.paymentBtn.setOnClickListener {
            if (adapter.validateAll())
                Toast.makeText(requireContext(), "asd", Toast.LENGTH_LONG).show()
        }

        val mask = PhoneWatcher(binding.phoneNumber)
        mask.setWatcher()

        viewModel.updateBookingInfo()
        viewModel.bookingInfo.observe(viewLifecycleOwner) { value ->
            when (value.status) {
                Status.SUCCESS -> {
                    value.data?.let {
                        with (binding) {
                            flightFromTv.text = it.departure
                            destinationTv.text = it.arrivalCountry
                            datesTv.text = getString(R.string.dates_window, it.tourDateStart, it.tourDateStop)
                            nightsNumberTv.text = it.nightsNumber.toString()
                            hotelTv.text = it.hotelName
                            roomTv.text = it.room
                            foodTv.text = it.nutrition
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
}