package com.example.booking.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        return view
    }
}