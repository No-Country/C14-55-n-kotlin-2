package com.symbiot.ipharma.ui.view.MainListProducts.recyclers

import androidx.recyclerview.widget.DiffUtil
import com.example.finanzas.domain.model.Categories

class CategoriesFilterDIffUtil(
    private val oldList: List<Categories>,
    private val newList: List<Categories>
) : DiffUtil.Callback() {
    //diffUtil
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
    }


}