package com.example.finanzas.domain.UseCase

import com.example.finanzas.domain.Repository
import com.example.finanzas.domain.model.Categories
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Categories> = repository.getCategories()
}