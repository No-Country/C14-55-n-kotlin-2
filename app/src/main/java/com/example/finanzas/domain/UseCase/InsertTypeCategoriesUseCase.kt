package com.example.finanzas.domain.UseCase

import com.example.finanzas.domain.Repository
import com.example.finanzas.domain.model.TypeCategories
import javax.inject.Inject

class InsertTypeCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        return repository.insertTypeCategory(
            listOf(
                TypeCategories(1, "Ingresos"),
                TypeCategories(2, "Egresos")
            )
        )
    }
}

