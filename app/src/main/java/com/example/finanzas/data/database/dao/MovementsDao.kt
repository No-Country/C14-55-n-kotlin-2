package com.example.finanzas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.finanzas.data.database.entities.CategoriesEntity
import com.example.finanzas.data.database.entities.MovementsEntity

@Dao
interface MovementsDao {
    @Insert()
    suspend fun insertMovements(movementsEntity: MovementsEntity)
    @Query("SELECT * FROM movements")
    suspend fun getMovements(): List<MovementsEntity>
}