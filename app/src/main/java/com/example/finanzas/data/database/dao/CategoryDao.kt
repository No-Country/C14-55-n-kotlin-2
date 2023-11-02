package com.example.finanzas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.finanzas.data.database.entities.CategoriesEntity

@Dao
interface CategoryDao {
    @Insert()
    suspend fun insertCategories(categoriesEntity: List<CategoriesEntity>)

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<CategoriesEntity>

}