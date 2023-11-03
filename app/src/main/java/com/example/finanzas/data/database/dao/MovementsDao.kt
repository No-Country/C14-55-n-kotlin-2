package com.example.finanzas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.finanzas.data.database.entities.MovementsEntity
import com.example.finanzas.data.database.entities.QueryGetMovementsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovementsDao {
    @Insert()
    suspend fun insertMovements(movementsEntity: MovementsEntity)
    @Query("SELECT movements.id, movements.value, movements.date,categories.id as idCategory, categories.name as nameCategory , " +
            "type_categories.id as idTypeCategory ,   categories.image FROM movements " +
            "inner join categories on movements.category_id = categories.id" +
            " inner join type_categories on categories.type_categories_id = type_categories.id order by movements.id desc")
    fun getMovements(): Flow<List<QueryGetMovementsEntity>>
}


