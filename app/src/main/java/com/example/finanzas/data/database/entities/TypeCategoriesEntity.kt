package com.example.finanzas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finanzas.domain.model.TypeCategories

@Entity(tableName = "type_categories")
data class TypeCategoriesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
)

fun TypeCategories.toDatabase() = TypeCategoriesEntity(
    id = id,
    name = name,
)
