package com.example.finanzas.domain.UseCase

import com.example.finanzas.R
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
                Categories( name="Sueldo", type_categories_id ="1", image = R.drawable.salario),
                Categories( name="Regalo", type_categories_id ="1",image = R.drawable.ic_gift),
                Categories( name="Alquiler", type_categories_id ="2",image = R.drawable.ic_home_lined),
                Categories( name="Comida", type_categories_id ="2", image =R.drawable.salida),
                Categories( name="Servicios", type_categories_id ="2",image = R.drawable.salida),
                Categories( name="Mercado", type_categories_id ="2",image = R.drawable.mercado),
                Categories( name="Salud", type_categories_id ="2", image =R.drawable.salud),
                Categories( name="Farmacia", type_categories_id ="2", image = R.drawable.ic_pharmacy),
                Categories( name="Salida", type_categories_id ="2", image =R.drawable.salida),
                Categories(name= "Ocio", type_categories_id = "2", image = R.drawable.ocio),
                Categories(name= "Viaje", type_categories_id ="2", image = R.drawable.viaje),
                Categories(name= "Auto", type_categories_id ="2", image = R.drawable.auto),
                Categories(name= "Mascota",type_categories_id = "2", image = R.drawable.mascota),
                )
        )
    }
}
