package com.example.finanzas.UI.movements

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzas.UI.movements.viewModel.MovementsViewModel
import com.example.finanzas.databinding.DialogAddEgressBinding
import com.example.finanzas.databinding.FragmentMovementsBinding
import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.Movements
import com.example.finanzas.domain.model.QueryGetMovements
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.symbiot.ipharma.ui.view.MainListProducts.recyclers.CategoriesAdapter
import com.symbiot.ipharma.ui.view.MainListProducts.recyclers.MovementsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovementsFragment : Fragment() {

    private var _binding: FragmentMovementsBinding? = null
    private val binding get() = _binding!!
    private lateinit var bindingDialogAddEgress: DialogAddEgressBinding
    private var dialogAddEgress: androidx.appcompat.app.AlertDialog? = null
    private var listOfCategories = listOf<Categories>()
    private val movementViewModel: MovementsViewModel by viewModels()
    private lateinit var movementsAdapter: MovementsAdapter
    private var stringList = listOf<String>()
    private var listOfMovements = listOf<QueryGetMovements>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovementsBinding.inflate(inflater, container, false)
        bindingDialogAddEgress = DialogAddEgressBinding.inflate(LayoutInflater.from(this.context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.rvFilterMovements
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        initUI()
        // Aquí puedes configurar el adaptador del RecyclerView y otros detalles según tus necesidades.
    }

    private fun initUI() {
        with(binding) {
            ivAnadirEgreso.setOnClickListener {
                showDialogAddEgress()
            }
        }
        initUiState()
        initRecyclers()

    }

    private fun initRecyclers() {
        movementsAdapter = MovementsAdapter{}
        setupRecyclerView(
            binding.rvMovements,
            RecyclerView.VERTICAL,
            movementsAdapter,
            requireContext(),
            1
        )
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movementViewModel.Movements.collect {
                    movementsAdapter.updateList(it)
                    listOfMovements = it
                    var sum = listOfMovements.filter { it.idTypeCategory.toInt() == 1 }.sumOf { it.value.toInt() }
                    var sum2 = listOfMovements.filter { it.idTypeCategory.toInt() == 2 }.sumOf { it.value.toInt() }
                    var balance = sum - sum2
                    binding.tvValueIncome.text = sum.toString()
                    binding.tvValueExpenses.text = sum2.toString()
                    binding.tvValueBalance.text = balance.toString()
                }
            }
        }
    }

    private fun initUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movementViewModel.Categories.collect {
                    listOfCategories = it
                    stringList = listOfCategories.map { it.name }
                    Log.d("Categories", it.toString())
                }
            }
        }

        lifecycleScope.launch {
            val getCategoriesJob = async { movementViewModel.getCategories() }
            getCategoriesJob.await()
            val getMovementsJob = async { movementViewModel.getMovements() }
            getMovementsJob.await()
        }
    }


    private fun showDialogAddEgress() {
        var dialogView = bindingDialogAddEgress.root


        dialogAddEgress = this.context?.let {
            MaterialAlertDialogBuilder(it)
                .setView(dialogView)
                .create()
        }
        (dialogView.parent as? ViewGroup)?.removeView(dialogView)


        val autoCompleteTextView = bindingDialogAddEgress.atCategoria

        // Crea un adaptador de sugerencias
        val adapter = this.context?.let {
            ArrayAdapter(
                it,
                R.layout.simple_dropdown_item_1line,
                stringList
            )
        }

        // Configura el adaptador en el AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter)

        bindingDialogAddEgress.btnDescartar.setOnClickListener {
            dialogAddEgress?.dismiss()
        }

        bindingDialogAddEgress.btnAgregar.setOnClickListener{
            val category = bindingDialogAddEgress.atCategoria.text.toString()
            val amount = bindingDialogAddEgress.etMonto.text.toString()
            val categorySelected = listOfCategories.firstOrNull { it.name == category }
            val categoryId = categorySelected?.id
            val movements = Movements(
                value = amount, category_id = categoryId.toString() , user_id = "1")
            lifecycleScope.launch {
                movementViewModel.insertMovements(movements)
            }
            dialogAddEgress?.dismiss()
        }

        dialogAddEgress?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupRecyclerView(
        recyclerView: RecyclerView,
        orientation: Int,
        adapter: RecyclerView.Adapter<*>,
        context: Context,
        spanCount: Int = 3
    ) {
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager = when (orientation) {
            RecyclerView.HORIZONTAL -> LinearLayoutManager(
                context,
                RecyclerView.HORIZONTAL,
                false
            )

            RecyclerView.VERTICAL -> GridLayoutManager(context, spanCount)
            else -> throw IllegalArgumentException("Invalid orientation")
        }
        recyclerView.adapter = adapter
    }
}