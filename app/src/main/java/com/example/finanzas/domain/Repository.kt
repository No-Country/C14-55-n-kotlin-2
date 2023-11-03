package com.example.finanzas.domain

import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.Movements
import com.example.finanzas.domain.model.TypeCategories
import com.example.finanzas.domain.model.Users

interface Repository {
    suspend fun insertTypeCategory(typeCategories: List<TypeCategories>)
    suspend fun insertCategories(categories: List<Categories>)
    suspend fun getCategories(): List<Categories>

    suspend fun insertUser(user: Users)

    suspend fun insertMovements(movements: Movements)
}