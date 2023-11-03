package com.example.finanzas.domain.UseCase

import com.example.finanzas.domain.Repository
import com.example.finanzas.domain.model.Movements
import javax.inject.Inject

class InsertMovements @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(movements: Movements) {
        return repository.insertMovements(movements)
    }
}