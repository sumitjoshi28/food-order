package com.sumit.foodorder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumit.foodorder.R
import com.sumit.foodorder.adapter.FoodItemAdapter
import com.sumit.foodorder.adapter.ItemClickAdapter
import com.sumit.foodorder.data.model.FoodItems
import com.sumit.foodorder.databinding.FragmentFoodSelectBinding
import com.sumit.foodorder.ui.viewmodel.FoodSelectViewModel

class FoodSelectFragment : Fragment(), ItemClickAdapter {
    private lateinit var binding: FragmentFoodSelectBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var viewModel: FoodSelectViewModel

    // set initial data for recycler view items. Hardcoding some data.
    private var foodItems: ArrayList<FoodItems> = arrayListOf(
        FoodItems("Chicken Tikka", 250, 0),
        FoodItems("Matar Paneer", 200, 0),
        FoodItems("Masala Dosa", 50, 0),
        FoodItems("Chicken Biryani", 200, 0),
        FoodItems("Veg Hakka Noodles", 150, 0),
        FoodItems("Egg Fried Rice", 150, 0),
    )

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

        // Get the View model
        viewModel = ViewModelProvider(this).get(FoodSelectViewModel::class.java)
        binding.foodSelectViewModel = viewModel

        // set recycler view adapter using data binding
        manager = LinearLayoutManager(this.requireContext())
        val adapter = FoodItemAdapter(foodItems, this@FoodSelectFragment)
        binding.recyclerFoodMenu.adapter = adapter
        binding.recyclerFoodMenu.layoutManager = manager

        // set observer for live data.
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, {
            if (it != null) {
                //adapter.updateList(it)
                adapter.setData(it)
            }
        })

        return binding.root
    }

    override fun onItemClicked(foodItem: FoodItems, itemPosition: Int) {
        viewModel.addButtonClick(itemPosition)
    }
}