package com.example.finanzas.domain.UseCase

import com.example.finanzas.domain.Repository
import com.example.finanzas.domain.model.Movements
import com.example.finanzas.domain.model.QueryGetMovements
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovements @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() : Flow<List<QueryGetMovements>> = repository.getMovements()
}