package com.symbiot.ipharma.ui.view.MainListProducts.recyclers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzas.R
import com.example.finanzas.databinding.ItemMovementsBinding
import com.example.finanzas.domain.model.QueryGetMovements

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMovementsBinding.bind(view)

    fun bind(movements: QueryGetMovements, onItemSelected: (Int) -> Unit) {

        getMovementColor(movements.idTypeCategory.toInt())
        if (movements.idTypeCategory.toInt() == 1) {
            binding.tvValueMovement.setTextColor(
                binding.tvValueMovement.context.resources.getColor(
                    R.color.Ingreso
                )
            )
            binding.ivMovementType.imageTintList =
                binding.ivMovementType.context.resources.getColorStateList(R.color.Ingreso)

        } else {
            binding.tvValueMovement.setTextColor(
                binding.tvValueMovement.context.resources.getColor(
                    R.color.Egreso
                )
            )
            binding.ivMovementType.imageTintList =
                binding.ivMovementType.context.resources.getColorStateList(R.color.Egreso)
        }

        binding.ivMovementType.setImageResource(movements.image)
        binding.tvValueMovement.text = movements.value
        binding.tvMovementDate.text = movements.date
        binding.tvMovementName.text = movements.nameCategory

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