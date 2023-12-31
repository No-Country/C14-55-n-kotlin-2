package com.example.finanzas.UI.summary.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanzas.domain.UseCase.GetCategoriesUseCase
import com.example.finanzas.domain.UseCase.GetMovements
import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.QueryGetMovements
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMovements: GetMovements
) : ViewModel() {

    private var _Categories = MutableStateFlow<List<Categories>>(emptyList())
    val Categories: StateFlow<List<Categories>> = _Categories

    private var _Movements = MutableStateFlow<List<QueryGetMovements>>(emptyList())
    val Movements: StateFlow<List<QueryGetMovements>> = _Movements



    suspend fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Categories", getCategoriesUseCase.invoke().toString())
            val categories: List<Categories> = getCategoriesUseCase.invoke()
            _Categories.value = categories
        }
    }



    suspend fun getMovements() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Movements", getMovements.invoke().toString())
            getMovements.invoke().collect { list ->
                Log.d("Movements", list.toString())
                _Movements.value = list
            }
        }
    }


}