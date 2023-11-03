package com.example.finanzas.domain.UseCase

import com.example.finanzas.domain.Repository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getCategories()
}