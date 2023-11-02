package com.example.finanzas.domain

import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.TypeCategories

interface Repository {
    suspend fun insertTypeCategory(typeCategories: List<TypeCategories>)
    suspend fun insertCategories(categories: List<Categories>)

}