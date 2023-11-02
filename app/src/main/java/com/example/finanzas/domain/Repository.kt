package com.example.finanzas.domain

import com.example.finanzas.domain.model.TypeCategories

interface Repository {
    suspend fun insertTypeCategory(typeCategories: TypeCategories)

}