package com.example.finanzas.domain.model

import com.example.finanzas.data.database.entities.MovementsEntity

data class Movements(
    val id: Int = 0,
    val value: String,
    val category_id: String,
    val user_id: String,
)

fun MovementsEntity.toDomain() = Movements(
    id = id,
    value = value,
    category_id = category_id,
    user_id = user_id,
)
