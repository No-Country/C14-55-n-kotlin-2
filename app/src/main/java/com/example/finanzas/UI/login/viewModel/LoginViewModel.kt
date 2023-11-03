package com.example.finanzas.UI.login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanzas.domain.UseCase.InsertCategoriesUseCase
import com.example.finanzas.domain.UseCase.InsertTypeCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val insertCategoriesUseCase: InsertCategoriesUseCase,
    private val insertTypeCategoriesUseCase: InsertTypeCategoriesUseCase,
) : ViewModel() {

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
}