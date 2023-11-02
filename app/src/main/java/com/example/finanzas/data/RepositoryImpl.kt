package com.example.finanzas.data

import android.database.sqlite.SQLiteConstraintException
import com.example.finanzas.data.database.dao.CategoryDao
import com.example.finanzas.data.database.dao.MovementsDao
import com.example.finanzas.data.database.dao.TypeCategoriesDao
import com.example.finanzas.data.database.dao.UsersDao
import com.example.finanzas.domain.Repository
import com.example.finanzas.domain.model.TypeCategories
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val categoryDao: CategoryDao,
    private val movementsDao: MovementsDao,
    private val typeCategoriesDao: TypeCategoriesDao
) : Repository {
    override suspend fun insertTypeCategory(typeCategories: TypeCategories) {
        try {
/*
            typeCategoriesDao.insertTypeCategories(typeCategories)
*/
        } catch (e: SQLiteConstraintException) {

        }
    }


}