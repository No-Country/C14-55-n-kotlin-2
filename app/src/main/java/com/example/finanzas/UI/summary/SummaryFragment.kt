package com.example.finanzas.UI.summary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.finanzas.UI.summary.viewModel.SummaryViewModel
import com.example.finanzas.databinding.DialogAddEgressBinding
import com.example.finanzas.databinding.FragmentSummaryBinding
import com.example.finanzas.domain.model.Categories
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private lateinit var bindingDialogAddEgress: DialogAddEgressBinding
    private var dialogAddEgress: androidx.appcompat.app.AlertDialog? = null
    private val binding get() = _binding!!
    private val summaryViewModel: SummaryViewModel by viewModels()
    private var listOfCategories = listOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        bindingDialogAddEgress = DialogAddEgressBinding.inflate(LayoutInflater.from(this.context))
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initUIState()
    }

    private fun initUI() {
        initList()
        initListeners()
    }

    private fun initUIState() {
/*
        realizar que esto se ejecute una sola vez
*/
        lifecycleScope.launch {
            summaryViewModel.insertTypeCategories()
            summaryViewModel.insertCategories()
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                summaryViewModel.Categories.collect {
                   listOfCategories = it
                    Log.d("Categories", it.toString())
                }
            }
        }
    }

    private fun initList() {

        val pieChart = binding.chart

        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(34f, "Japon"))
        entries.add(PieEntry(23f, "USA"))
        entries.add(PieEntry(14f, "UK"))
        entries.add(PieEntry(35f, "India"))
        entries.add(PieEntry(40f, "China"))
        entries.add(PieEntry(23f, "Brasil"))

        val dataSet = PieDataSet(entries, "Ejemplo de PieChart")

        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.invalidate() // refresca el gráfico

    }

    private fun initListeners() {
        with(binding) {
            ivAnadirEgreso.setOnClickListener {
                lifecycleScope.launch {
                    summaryViewModel.getCategories()
                }
                showDialogAddEgress()
            }
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
                android.R.layout.simple_dropdown_item_1line,
                listOfCategories
            )
        }

        // Configura el adaptador en el AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter)

        bindingDialogAddEgress.btnDescartar.setOnClickListener {
            dialogAddEgress?.dismiss()
        }

        dialogAddEgress?.show()
    }


}