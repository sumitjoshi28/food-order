package com.sumit.foodorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumit.foodorder.data.model.FoodItems
import com.sumit.foodorder.databinding.FoodItemsBinding

class FoodItemAdapter(private var items: MutableList<FoodItems>) :
    RecyclerView.Adapter<FoodItemAdapter.ViewHolderFoodItems>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFoodItems {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = FoodItemsBinding.inflate(inflater, parent, false)
        return ViewHolderFoodItems(listItemBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolderFoodItems, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolderFoodItems(private val binding: FoodItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: FoodItems) {
            binding.listItem = items
        }
    }
}
