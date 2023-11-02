package com.example.finanzas.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finanzas.data.database.dao.CategoryDao
import com.example.finanzas.data.database.dao.MovementsDao
import com.example.finanzas.data.database.dao.TypeCategoriesDao
import com.example.finanzas.data.database.dao.UsersDao
import com.example.finanzas.data.database.entities.CategoriesEntity
import com.example.finanzas.data.database.entities.MovementsEntity
import com.example.finanzas.data.database.entities.UsersEntity

import com.example.finanzas.data.database.entities.TypeCategoriesEntity


@Database(
    entities = [
        TypeCategoriesEntity::class,
        MovementsEntity::class,
        UsersEntity::class,
        CategoriesEntity::class
               ],
    version = 1)
abstract class FinanzasDB : RoomDatabase(){
    abstract fun getTypeCategoriesDao(): TypeCategoriesDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getUsers(): UsersDao
    abstract fun getMovements(): MovementsDao
}