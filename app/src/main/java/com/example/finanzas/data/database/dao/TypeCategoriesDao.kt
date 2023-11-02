package com.example.finanzas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.finanzas.data.database.entities.CategoriesEntity
import com.example.finanzas.data.database.entities.TypeCategoriesEntity

@Dao
interface TypeCategoriesDao {
    @Insert()
    suspend fun insertTypeCategories(typeCategoriesEntity: List<TypeCategoriesEntity>)

    @Query("SELECT * FROM type_categories")
    suspend fun getTypeCategories(): List<TypeCategoriesEntity>
}