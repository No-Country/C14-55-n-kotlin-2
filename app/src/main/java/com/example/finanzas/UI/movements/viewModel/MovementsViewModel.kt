package com.example.finanzas.UI.movements.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanzas.domain.UseCase.GetCategoriesUseCase
import com.example.finanzas.domain.UseCase.GetMovements
import com.example.finanzas.domain.UseCase.InsertMovements
import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.Movements
import com.example.finanzas.domain.model.QueryGetMovements
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovementsViewModel @Inject constructor(
    private val insertMovements: InsertMovements,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMovements: GetMovements
): ViewModel() {

    private var _Categories = MutableStateFlow<List<Categories>>(emptyList())
    val Categories: StateFlow<List<Categories>> = _Categories

    private var _Movements = MutableStateFlow<List<QueryGetMovements>>(emptyList())
    val Movements: StateFlow<List<QueryGetMovements>> = _Movements


    suspend fun insertMovements(movements: Movements) {
        viewModelScope.launch(Dispatchers.IO) {
            insertMovements.invoke(movements)

        }
    }

    suspend fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Categories", getCategoriesUseCase.invoke().toString())
            val categories: List<Categories> = getCategoriesUseCase.invoke()
            _Categories.value = categories
        }
    }

    suspend fun getMovements() {
        viewModelScope.launch(Dispatchers.IO) {
            getMovements.invoke().collect { list ->
                Log.d("Movements", list.toString())
                _Movements.value = list
            }
        }
    }
}