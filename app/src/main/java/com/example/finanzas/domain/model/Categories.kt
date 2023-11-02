package com.example.finanzas.domain.model

import com.example.finanzas.data.database.entities.CategoriesEntity


data class Categories(
    val id: Int = 0,
    val name: String,
    val type_categories_id: String,
)

fun CategoriesEntity.toDatabase() = Categories(
    id = id,
    name = name,
    type_categories_id = type_categories_id,
)
