package com.example.finanzas.UI.summary.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanzas.domain.UseCase.GetCategoriesUseCase
import com.example.finanzas.domain.UseCase.InsertCategoriesUseCase
import com.example.finanzas.domain.UseCase.InsertTypeCategoriesUseCase
import com.example.finanzas.domain.model.Categories
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
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private var _Categories = MutableStateFlow<List<String>>(emptyList())
    val Categories: StateFlow<List<String>> = _Categories


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
            _Categories.value = getCategoriesUseCase.invoke()
        }
    }


}