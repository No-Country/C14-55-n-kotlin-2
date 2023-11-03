package com.example.finanzas.domain.model

import com.example.finanzas.data.database.entities.QueryGetMovementsEntity


data class QueryGetMovements (
    val id : Int,
    val value: String,
    val date: String,
    val nameCategory: String,
    val idCategory: String,
    val idTypeCategory: String,
    val image : Int
)



fun QueryGetMovementsEntity.toDomain() = QueryGetMovements(
    id = id,
    value = value,
    date = date,
    idCategory = idCategory,
    nameCategory = nameCategory,
    idTypeCategory = idTypeCategory,
    image = image
)

