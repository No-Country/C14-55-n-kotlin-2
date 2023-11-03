package com.example.finanzas.UI.register.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finanzas.domain.UseCase.InsertUsersUseCase
import com.example.finanzas.domain.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrerViewModel @Inject constructor(
   private val insertUsersUseCase: InsertUsersUseCase
) : ViewModel() {

    suspend fun insertUsersCase(email: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            insertUsersUseCase.invoke(email)
        }
    }


}