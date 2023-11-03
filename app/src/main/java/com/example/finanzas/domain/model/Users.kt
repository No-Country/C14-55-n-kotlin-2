package com.example.finanzas.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Users (
     val id: Int = 0,
     val email: String,
)