package com.example.booking.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.booking.R
import kotlin.random.Random

class FinishFragment : Fragment() {
    private val orderNumber = Random(0).nextInt()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finish, container, false)
        view.findViewById<TextView>(R.id.order_info).text = getString(R.string.order_info, orderNumber)
        view.findViewById<ImageButton>(R.id.back_to_booking).setOnClickListener {
            findNavController().popBackStack()
        }
        view.findViewById<Button>(R.id.back_to_main).setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().popBackStack()
        }

//        findNavController().popBackStack(R.id.action_bookingFragment_to_finishFragment, false)
        return view
    }
}