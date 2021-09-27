package com.sumit.foodorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumit.foodorder.data.model.FoodItems
import com.sumit.foodorder.databinding.FoodItemsBinding
import com.sumit.foodorder.ui.viewmodel.FoodSelectViewModel

class FoodItemAdapter(var items: ArrayList<FoodItems>, val viewModel: FoodSelectViewModel) :
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
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    // update recyclerview data using diff util class.
    fun setData(newFoodList: ArrayList<FoodItems>) {
        val diffUtil = DiffCallback(items, newFoodList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        items = newFoodList
        diffResults.dispatchUpdatesTo(this)
    }

    inner class ViewHolderFoodItems(private val binding: FoodItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: FoodItems) {
            // data binding to the xml
            binding.listItem = currentItem
            binding.executePendingBindings()

            binding.addItem.setOnClickListener {
                if (currentItem.count <= 19) {
                    currentItem.count++

                    viewModel.update(currentItem)
                    notifyDataSetChanged()
                }
            }

            binding.removeItem.setOnClickListener {
                if (currentItem.count > 0) {
                    currentItem.count--
                    viewModel.update(currentItem)
                    notifyDataSetChanged()
                }
            }
        }
    }
}

// New improved way of updating recycler view data,later can be implemented.
class DiffCallback(
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
            }
            else -> {
                true
            }
        }
    }
}
