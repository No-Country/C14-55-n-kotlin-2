package com.example.finanzas.domain

import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.Movements
import com.example.finanzas.domain.model.QueryGetMovements
import com.example.finanzas.domain.model.TypeCategories
import com.example.finanzas.domain.model.Users
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertTypeCategory(typeCategories: List<TypeCategories>)
    suspend fun insertCategories(categories: List<Categories>)
    suspend fun getCategories(): List<Categories>

    suspend fun insertUser(user: Users)

    suspend fun insertMovements(movements: Movements)

    fun getMovements(): Flow<List<QueryGetMovements>>
}