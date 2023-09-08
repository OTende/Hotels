package com.example.room.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.util.Status
import com.example.room.R
import com.example.room.databinding.FragmentRoomBinding
import com.example.room.di.DaggerFeatureRoomComponent
import com.example.room.presentation.adapter.PeculiarityAdapter
import com.example.room.presentation.adapter.RoomAdapter
import com.example.room.presentation.viewmodel.RoomViewModel
import javax.inject.Inject

class RoomFragment : Fragment() {
    private var _binding: FragmentRoomBinding? = null

    @Inject
    lateinit var viewModel: RoomViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFeatureRoomComponent.builder().build().inject(this)
    }

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        viewModel.updateRoomsList()
        viewModel.rooms.observe(viewLifecycleOwner) { value ->
            when (value.status) {
                Status.SUCCESS -> {
                    binding.hotelName.text = arguments?.getString("hotelName")
                    val adapter = value.data?.let { RoomAdapter(it, resources) }
                    binding.roomRv.adapter = adapter
                    binding.roomRv.layoutManager = LinearLayoutManager(requireContext())
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), value.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    Toast.makeText(requireContext(), value.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}