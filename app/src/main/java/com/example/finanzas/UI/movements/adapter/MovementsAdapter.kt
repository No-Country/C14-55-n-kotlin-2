package com.symbiot.ipharma.ui.view.MainListProducts.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzas.R
import com.example.finanzas.domain.model.QueryGetMovements

class MovementsAdapter(
    var movementsList: List<QueryGetMovements> = emptyList(),
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<MovementsViewHolder>() {

    private var filteredProductsList: List<QueryGetMovements> = movementsList

    fun updateList(list: List<QueryGetMovements>) {
        val diffUtil = MovementsDIffUtil(movementsList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        movementsList = list
        filteredProductsList = list
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementsViewHolder {
        return MovementsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movements, parent, false)
        )
    }

    fun setFilterByCategory(categoryId: Int?) {
        val newList = if (categoryId == null) {
            movementsList
        } else {
            movementsList.filter { it.idCategory.toInt() == categoryId}
        }

        val diffCallback = MovementsDIffUtil(filteredProductsList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        filteredProductsList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(viewholder: MovementsViewHolder, position: Int) {
        viewholder.bind(filteredProductsList[position], onItemSelected)
    }

    override fun getItemCount() = filteredProductsList.size

    private fun getItem(position: Int): QueryGetMovements {
        return filteredProductsList[position]
    }

}