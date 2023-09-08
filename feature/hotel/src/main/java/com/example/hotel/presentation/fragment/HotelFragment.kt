package com.example.hotel.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.core.domain.util.Status
import com.example.hotel.R
import com.example.hotel.databinding.FragmentHotelBinding
import com.example.hotel.di.DaggerFeatureHotelComponent
import com.example.hotel.presentation.adapter.PeculiarityAdapter
import com.example.hotel.presentation.adapter.PhotoAdapter
import com.example.hotel.presentation.viewmodel.HotelViewModel
import javax.inject.Inject

class HotelFragment : Fragment() {
    @Inject
    lateinit var viewModel: HotelViewModel

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFeatureHotelComponent.builder().build().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        viewModel.updateHotel()
        viewModel.hotel.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let {
                        with(binding) {
                            hotelName.text = it.name
                            hotelRating.text = getString(R.string.rating, it.rating, it.ratingName)
                            address.text = it.address
                            price.text = getString(R.string.price, it.minimalPrice)
                            priceDescription.text = it.priceDescription
                            description.text = it.hotelAbout.description
                            binding.photos.adapter = PhotoAdapter(it.imageUrls)
                            binding.peculiarities.adapter = PeculiarityAdapter(it.hotelAbout.peculiarities)
                            binding.peculiarities.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
                        }
                    }
                }

                Status.ERROR -> {
//                    binding.asd.text = result.message
                }
                Status.LOADING -> {
//                    binding.asd.text = "is loading"
                }
//                binding.hotelRatingCv.findViewById<TextView>()
            }
        }
        return binding.root
    }
}