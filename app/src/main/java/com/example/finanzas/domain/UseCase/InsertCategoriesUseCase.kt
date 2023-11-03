package com.example.finanzas.domain.UseCase

import com.example.finanzas.domain.Repository
import com.example.finanzas.domain.model.Categories
import javax.inject.Inject

class InsertCategoriesUseCase
@Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        return repository.insertCategories(
            listOf(
                Categories(1, "Sueldo", "1"),
                Categories(2, "Ahorro", "1"),
                Categories(3, "Otros", "1"),
                Categories(4, "Alquiler", "2"),
                Categories(5, "Comida", "2"),
                Categories(6, "Servicios", "2"),
                Categories(7, "Casa", "2"),
                Categories(8, "Mercado", "2"),
                Categories(9, "Salud", "2"),
                Categories(10, "Farmacia", "2"),
                Categories(11, "Regalo", "1"),
                Categories(12, "Salida", "2"),
                Categories(13, "Ocio", "2"),
                Categories(14, "Viaje", "2"),
                Categories(15, "Auto", "2"),
                Categories(16, "Mascota", "2"),
                Categories(17, "Educacion", "2"),

                )
        )
    }
}
