package com.example.finanzas.UI.summary

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzas.UI.summary.viewModel.SummaryViewModel
import com.example.finanzas.databinding.FragmentSummaryBinding
import com.example.finanzas.domain.model.QueryGetMovements
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.symbiot.ipharma.ui.view.MainListProducts.recyclers.CategoriesAdapter
import com.symbiot.ipharma.ui.view.MainListProducts.recyclers.CategoriesFilterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding? = null

    private val binding get() = _binding!!
    private val summaryViewModel: SummaryViewModel by viewModels()
    private var listOfMovements = listOf<QueryGetMovements>()
    private lateinit var movementsAdapter: CategoriesAdapter
    private var stringList = listOf<String>()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initUIState()
    }

    private fun initUI() {
        initRecyclers()
    }

    private fun initRecyclers() {
        movementsAdapter = CategoriesAdapter{}

        setupRecyclerView(
            binding.rvMovements,
            RecyclerView.VERTICAL,
            movementsAdapter,
            requireContext(),
            1
        )
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                summaryViewModel.Movements.collect {
                    movementsAdapter.updateList(it)
                    listOfMovements = it
                    var sum = listOfMovements.filter { it.idTypeCategory.isNotEmpty() && it.idTypeCategory.toInt() == 1 && it.value.isNotEmpty() }.sumOf { it.value.toInt() }
                    var sum2 = listOfMovements.filter { it.idTypeCategory.isNotEmpty() && it.idTypeCategory.toInt() == 2 && it.value.isNotEmpty() }.sumOf { it.value.toInt() }
                    var balance = sum - sum2
                    binding.tvValueIncome.text = sum.toString()
                    binding.tvValueExpenses.text = sum2.toString()
                    binding.tvValueBalance.text = balance.toString()



            /*       var torta = listOfMovements.filter { it.idTypeCategory.toInt() == 2 }
                    Log.d("Torta", torta.toString())
                    val totalValue = torta.sumBy { it.value.toInt() }

                    val results = listOfMovements.filter { it.idTypeCategory.toInt() == 2 }.map { category ->
                        Pair(category, percentage)
                    }
                    val percentage = totalValueForCategory.toDouble() / totalValue * 100


                    Log.d("Results", results.toString())


                    Log.d("Torta", totalValue.toString())*/
                    val pieChart = binding.chart

                    val entries = ArrayList<PieEntry>()
                    entries.add(PieEntry(34f, "Japon"))
                    entries.add(PieEntry(23f, "Brasil"))

                    val dataSet = PieDataSet(entries, "")

                    dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

                    val data = PieData(dataSet)
                    pieChart.data = data
                    pieChart.invalidate() // refresca el gráfico
                }
            }
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            val getCategoriesJob = async { summaryViewModel.getCategories() }
            val getMovementsJob = async { summaryViewModel.getMovements() }
            getCategoriesJob.await()
            getMovementsJob.await()
        }

/*
        realizar que esto se ejecute una sola vez
*/
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                summaryViewModel.Categories.collect {
                    stringList = it.map { it.name }
                    Log.d("Categories", it.toString())
                }
            }
        }



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