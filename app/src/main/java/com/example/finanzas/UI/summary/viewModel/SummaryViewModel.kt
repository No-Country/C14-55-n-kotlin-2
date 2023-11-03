package com.example.finanzas.UI.summary.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanzas.domain.UseCase.GetCategoriesUseCase
import com.example.finanzas.domain.UseCase.InsertCategoriesUseCase
import com.example.finanzas.domain.UseCase.InsertMovements
import com.example.finanzas.domain.UseCase.InsertTypeCategoriesUseCase
import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.Movements
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val insertCategoriesUseCase: InsertCategoriesUseCase,
    private val insertTypeCategoriesUseCase: InsertTypeCategoriesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val insertMovements: InsertMovements
) : ViewModel() {

    private var _Categories = MutableStateFlow<List<Categories>>(emptyList())
    val Categories: StateFlow<List<Categories>> = _Categories


    suspend fun insertTypeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            insertTypeCategoriesUseCase.invoke()
        }
    }

    suspend fun insertCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            insertCategoriesUseCase.invoke()
        }
    }

    suspend fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("Categories", getCategoriesUseCase.invoke().toString())
            val categories: List<Categories> = getCategoriesUseCase.invoke()
            _Categories.value = categories
        }
    }

    suspend fun insertMovements(movements: Movements) {
        viewModelScope.launch(Dispatchers.IO) {
            insertMovements.invoke(movements)
        }
    }


}