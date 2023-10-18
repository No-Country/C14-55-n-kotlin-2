package com.example.finanzas.UI.movements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finanzas.databinding.FragmentMovementsBinding


class MovementsFragment : Fragment() {
    private var _binding: FragmentMovementsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovementsBinding.inflate(inflater, container, false)
        return binding.root
    }


}