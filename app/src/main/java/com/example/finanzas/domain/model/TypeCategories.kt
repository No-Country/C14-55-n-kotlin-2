package com.example.finanzas.domain.model

import com.example.finanzas.data.database.entities.TypeCategoriesEntity


data class TypeCategories(
  val id: Int = 0,
  val name: String,
)


fun TypeCategoriesEntity.toDomain() = TypeCategories(
  id = id,
  name = name,
)
