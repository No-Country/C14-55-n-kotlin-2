package com.example.finanzas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movements",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = CategoriesEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = androidx.room.ForeignKey.CASCADE
        ),
        androidx.room.ForeignKey(
            entity = UsersEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
)
data class MovementsEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "category_id") val category_id: String,
    @ColumnInfo(name = "user_id") val user_id: String,
    @ColumnInfo(name = "date") val date: String,
)