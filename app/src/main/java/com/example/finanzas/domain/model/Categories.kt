package com.example.finanzas.domain.model

import com.example.finanzas.data.database.entities.CategoriesEntity


data class Categories(
    val id: Int = 0,
    val name: String,
    val type_categories_id: String,
    val image: Int
)

fun CategoriesEntity.toDomain() = Categories(
    id = id,
    name = name,
    type_categories_id = type_categories_id,
    image = image
)
