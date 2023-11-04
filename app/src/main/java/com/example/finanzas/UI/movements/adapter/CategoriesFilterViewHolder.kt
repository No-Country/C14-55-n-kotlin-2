package com.symbiot.ipharma.ui.view.MainListProducts.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzas.R
import com.example.finanzas.databinding.ItemMovementsFilterBinding
import com.example.finanzas.domain.model.Categories

class CategoriesFilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMovementsFilterBinding.bind(view)

    fun bind(movements: Categories, onItemSelected: (Int) -> Unit) {

        binding.ivMovementType.setImageResource(movements.image)
        binding.tvMovementName.text = movements.name
        getMovementColor(movements.type_categories_id.toInt())
        if (movements.type_categories_id.toInt() == 1) {
            binding.tvMovementName.setTextColor(
                binding.ivMovementType.context.resources.getColor(
                    R.color.Ingreso
                )
            )
            binding.ivMovementType.imageTintList =
                binding.ivMovementType.context.resources.getColorStateList(R.color.Ingreso)

        } else {
            binding.tvMovementName.setTextColor(
                binding.ivMovementType.context.resources.getColor(
                    R.color.Egreso
                )
            )
            binding.ivMovementType.imageTintList =
                binding.ivMovementType.context.resources.getColorStateList(R.color.Egreso)
        }

        binding.root.setOnClickListener {
            onItemSelected(movements.id)
        }

    }


   private fun getMovementColor(id_category: Int): Int {
        return when (id_category) {
            1 -> R.color.Ingreso
            2 -> R.color.Egreso
            else -> {
                R.color.black
            }
        }
    }


}