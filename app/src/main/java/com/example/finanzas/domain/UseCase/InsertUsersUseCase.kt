package com.example.finanzas.domain.UseCase

import com.example.finanzas.domain.Repository
import com.example.finanzas.domain.model.Users
import javax.inject.Inject

class InsertUsersUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(email: Users) {
        return repository.insertUser(email)
    }
}