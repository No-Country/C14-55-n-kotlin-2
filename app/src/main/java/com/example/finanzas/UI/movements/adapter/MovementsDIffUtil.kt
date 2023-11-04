package com.symbiot.ipharma.ui.view.MainListProducts.recyclers

import androidx.recyclerview.widget.DiffUtil
import com.example.finanzas.domain.model.Categories
import com.example.finanzas.domain.model.QueryGetMovements

class MovementsDIffUtil(
    private val oldList: List<QueryGetMovements>,
    private val newList: List<QueryGetMovements>
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