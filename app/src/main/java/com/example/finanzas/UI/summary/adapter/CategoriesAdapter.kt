package com.symbiot.ipharma.ui.view.MainListProducts.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzas.R
import com.example.finanzas.domain.model.Movements
import com.example.finanzas.domain.model.QueryGetMovements

class CategoriesAdapter(
    var categoriasList: List<QueryGetMovements> = emptyList(),
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<CategoriesViewHolder>() {


    fun updateList(list: List<QueryGetMovements>) {
        val diffUtil = CategoriesDIffUtil(categoriasList, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        categoriasList = list
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movements, parent, false)
        )
    }

    override fun onBindViewHolder(viewholder: CategoriesViewHolder, position: Int) {
        viewholder.bind(getItem(position), onItemSelected)
    }

    override fun getItemCount() = categoriasList.size

    private fun getItem(position: Int): QueryGetMovements {
        return categoriasList[position]
    }

}