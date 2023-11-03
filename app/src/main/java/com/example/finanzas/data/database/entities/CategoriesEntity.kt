package com.example.finanzas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.TypeCategories

@Entity(tableName = "categories",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = TypeCategoriesEntity::class,
            parentColumns = ["id"],
            childColumns = ["type_categories_id"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
data class CategoriesEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo("type_categories_id") val type_categories_id: String,
    @ColumnInfo("image") val image: Int
)

fun Categories.toDatabase() = CategoriesEntity(
    id = id,
    name = name,
    type_categories_id = type_categories_id,
    image = image
)
