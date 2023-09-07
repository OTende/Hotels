package com.example.hotel.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.core.domain.util.Status
import com.example.hotel.R
import com.example.hotel.di.DaggerFeatureHotelComponent
import com.example.hotel.presentation.viewmodel.HotelViewModel
import javax.inject.Inject

class HotelFragment : Fragment() {
    @Inject
    lateinit var viewModel: HotelViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (requireActivity().applicationContext as Asd).
//        (requireActivity().applicationContext as MusicApplication).appComponent.inject(this)
//        FeatureHotelModule.Bui
        DaggerFeatureHotelComponent.builder().build().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hotel, container, false)
        val asd = view.findViewById<TextView>(R.id.asd)
        viewModel.updateHotel()
        viewModel.hotel.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    asd.text = result.data?.name
                }

                Status.ERROR -> {
                    asd.text = result.message
                }
                Status.LOADING -> {
                    asd.text = "is loading"
                }
            }
        }
        return view
    }
}