package com.sumit.foodorder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumit.foodorder.data.model.FoodItems
import com.sumit.foodorder.databinding.FoodItemsBinding

class FoodItemAdapter(private var items: ArrayList<FoodItems>, val listener: ItemClickAdapter) :
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

//    fun updateList(newList:ArrayList<FoodItems>){
//        items.clear()
//        items.addAll(newList)
//    }

    fun setData(newFoodList: ArrayList<FoodItems>) {
        val diffUtil = DiffCallback(items,newFoodList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        items = newFoodList
        diffResults.dispatchUpdatesTo(this)
    }

    inner class ViewHolderFoodItems(private val binding: FoodItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(items: FoodItems) {
            binding.listItem = items
            binding.executePendingBindings()

            binding.count.text = items.count.toString()

            binding.addItemBtn.setOnClickListener {
                if (items.count >= 1) {
                    binding.addItem.visibility = View.VISIBLE
                    binding.count.visibility = View.VISIBLE
                    binding.removeItem.visibility = View.VISIBLE
                    binding.addItemBtn.visibility = View.GONE
                }else {
                    binding.addItem.visibility = View.GONE
                    binding.count.visibility = View.GONE
                    binding.removeItem.visibility = View.GONE
                    binding.addItemBtn.visibility = View.VISIBLE
                }
                listener.onItemClicked(items,adapterPosition)
            }
        }
    }
}

class DiffCallback (
    private val oldList: List<FoodItems>,
    private val newList: List<FoodItems>
        ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].count == newList[newItemPosition].count
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].count != newList[newItemPosition].count -> {
                false
            }else -> {
                true
            }
        }
    }


}

interface ItemClickAdapter {
    fun onItemClicked(foodItem: FoodItems,itemPosition:Int)
}
