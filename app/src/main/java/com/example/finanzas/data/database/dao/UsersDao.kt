package com.example.finanzas.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.finanzas.data.database.entities.TypeCategoriesEntity
import com.example.finanzas.data.database.entities.UsersEntity

@Dao
interface UsersDao {
    @Insert()
    suspend fun insertUsers(usersEntity: UsersEntity)
/*    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<TypeCategoriesEntity>*/

}