package com.example.finanzas.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.Users

@Entity(tableName = "users")
data class UsersEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "email") val email: String,
)

fun Users.toDatabase() = UsersEntity(
    id = id,
    email = email,
)