package com.example.finanzas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finanzas.domain.model.Movements
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "category_id") val category_id: String,
    @ColumnInfo(name = "user_id") val user_id: String,
    @ColumnInfo(name = "date") val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
)

fun Movements.toDatabase() = MovementsEntity(
    id = id,
    value = value,
    category_id = category_id,
    user_id = user_id,
)