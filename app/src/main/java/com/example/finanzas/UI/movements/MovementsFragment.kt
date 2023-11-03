package com.example.finanzas.UI.movements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finanzas.data.database.entities.MovementsEntity
import com.example.finanzas.databinding.FragmentMovementsBinding


class MovementsFragment : Fragment() {

    private var _binding: FragmentMovementsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovementsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvFilterMovements
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // Aquí puedes configurar el adaptador del RecyclerView y otros detalles según tus necesidades.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}