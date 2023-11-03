package com.example.finanzas.data

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.example.finanzas.data.database.dao.CategoryDao
import com.example.finanzas.data.database.dao.MovementsDao
import com.example.finanzas.data.database.dao.TypeCategoriesDao
import com.example.finanzas.data.database.dao.UsersDao
import com.example.finanzas.data.database.entities.toDatabase
import com.example.finanzas.domain.Repository
import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.Movements
import com.example.finanzas.domain.model.QueryGetMovements
import com.example.finanzas.domain.model.TypeCategories
import com.example.finanzas.domain.model.Users
import com.example.finanzas.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val categoryDao: CategoryDao,
    private val movementsDao: MovementsDao,
    private val typeCategoriesDao: TypeCategoriesDao
) : Repository {
    override suspend fun insertTypeCategory(typeCategories: List<TypeCategories>) {
        try {
            typeCategoriesDao.insertTypeCategories(
                typeCategories.map { it.toDatabase() }
            )
        } catch (e: SQLiteConstraintException) {
            Log.d("Error al insertar insertTypeCategories ", e.toString())
        }
    }


    override suspend fun insertCategories(categories: List<Categories>) {
        try {
            categoryDao.insertCategories(
                categories.map { it.toDatabase() }
            )
        } catch (e: SQLiteConstraintException) {
            Log.d("Error al insertar insertCategories ", e.toString())
        }
    }

    override suspend fun getCategories(): List<Categories> {
        return categoryDao.getCategories().map { it.toDomain() }
    }

    override suspend fun insertUser(user: Users) {
        try {
            usersDao.insertUsers(
                user.toDatabase()
            )
        } catch (e: SQLiteConstraintException) {
            Log.d("Error al insertar insertCategories ", e.toString())
        }
    }

    override suspend fun insertMovements(movements: Movements) {
        try {
            movementsDao.insertMovements(movements.toDatabase())
        } catch (e: SQLiteConstraintException) {
            Log.d("Error al insertar insertMovements ", e.toString())
        }
    }

    override fun getMovements(): Flow<List<QueryGetMovements>> {
        return movementsDao.getMovements().map { list ->
            list.map { movementsEntity ->
                movementsEntity.toDomain()
            }
        }
    }


}