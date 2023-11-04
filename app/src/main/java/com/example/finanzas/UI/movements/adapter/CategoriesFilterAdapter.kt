package com.symbiot.ipharma.ui.view.MainListProducts.recyclers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.finanzas.R
import com.example.finanzas.domain.model.Categories

class CategoriesFilterAdapter(
    var Categorieslist: List<Categories> = emptyList(),
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<CategoriesFilterViewHolder>() {


    fun updateList(list: List<Categories>) {
        val diffUtil = CategoriesFilterDIffUtil(Categorieslist, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        Categorieslist = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesFilterViewHolder {
        return CategoriesFilterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movements_filter, parent, false)
        )
    }

    override fun onBindViewHolder(viewholder: CategoriesFilterViewHolder, position: Int) {
        viewholder.bind(getItem(position), onItemSelected)
    }

    override fun getItemCount() = Categorieslist.size

    private fun getItem(position: Int): Categories {
        return Categorieslist[position]
    }

}