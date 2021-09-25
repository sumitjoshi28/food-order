package com.sumit.foodorder.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumit.foodorder.R
import com.sumit.foodorder.adapter.FoodItemAdapter
import com.sumit.foodorder.data.model.FoodItems
import com.sumit.foodorder.databinding.FragmentFoodSelectBinding

class FoodSelectFragment : Fragment() {
    private lateinit var binding: FragmentFoodSelectBinding
    private lateinit var manager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_food_select, container, false
        )

        // Bind this fragment class to the layout
        binding.foodSelect = this

        // set data for recycler view items
        val foodItems: MutableList<FoodItems> = mutableListOf(
            FoodItems("Chicken Tikka", 250, 0),
            FoodItems("Matar Paneer", 200, 0),
            FoodItems("Masala Dosa", 50, 0),
            FoodItems("Chicken Biryani", 200, 0),
            FoodItems("Veg Hakka Noodles", 150, 0),
            FoodItems("Egg Fried Rice", 150, 0),
        )

        // set recycler view adapter using data binding
        manager = LinearLayoutManager(this.requireContext())
        binding.recyclerFoodMenu.apply {
            adapter = FoodItemAdapter(foodItems)
            layoutManager = manager
        }

        return binding.root
    }
}