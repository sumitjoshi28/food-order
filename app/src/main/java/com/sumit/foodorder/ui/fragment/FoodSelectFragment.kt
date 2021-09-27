package com.sumit.foodorder.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumit.foodorder.R
import com.sumit.foodorder.adapter.FoodItemAdapter
import com.sumit.foodorder.data.db.FoodItemDatabase
import com.sumit.foodorder.data.model.FoodItems
import com.sumit.foodorder.data.repository.FoodItemRepository
import com.sumit.foodorder.databinding.FragmentFoodSelectBinding
import com.sumit.foodorder.ui.viewmodel.FoodSelectViewModel
import com.sumit.foodorder.ui.viewmodel.FoodSelectViewModelFactory

class FoodSelectFragment : Fragment() {
    private lateinit var binding: FragmentFoodSelectBinding
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var viewModel: FoodSelectViewModel

    // set initial data for recycler view items. Hardcoding some data for now.
    private var foodItems: ArrayList<FoodItems> = arrayListOf(
        FoodItems("Chicken Tikka", 250, 0, 1),
        FoodItems("Matar Paneer", 200, 0, 2),
        FoodItems("Masala Dosa", 50, 0, 3),
        FoodItems("Chicken Biryani", 200, 0, 4),
        FoodItems("Veg Hakka Noodles", 150, 0, 5),
        FoodItems("Egg Fried Rice", 150, 0, 6),
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

        // set db and repo with view model factory
        val database = FoodItemDatabase(this.requireContext())
        val repository = FoodItemRepository(database)
        val factory = FoodSelectViewModelFactory(repository)

        // Get the View model
        viewModel = ViewModelProvider(this, factory).get(FoodSelectViewModel::class.java)
        binding.foodSelectViewModel = viewModel

        // set initial data.
        setInitData()

        // set recycler view adapter using data binding
        manager = LinearLayoutManager(this.requireContext())
        val adapter = FoodItemAdapter(foodItems, viewModel)
        binding.recyclerFoodMenu.adapter = adapter
        binding.recyclerFoodMenu.layoutManager = manager

        // set observer for live data.
        viewModel.getAllFoodItems().observe(viewLifecycleOwner, Observer {
            adapter.items = it as ArrayList<FoodItems>
        })

        val count = viewModel.getCount()
        viewModel.totalCount.observe(viewLifecycleOwner, Observer {
            binding.navigateToNext.text = "VIEW CART (${it.toString()} ITEMS)"
        })

        var totalPrice = viewModel.getTotalPrice().value.toString()
        viewModel.totalP.observe(viewLifecycleOwner,{
            totalPrice = it.toString()
        })

        // set listener for view cart button
        binding.navigateToNext.setOnClickListener {
            val bundle = bundleOf("price" to totalPrice)
            findNavController().navigate(R.id.action_foodSelectFragment_to_foodCartFragment,bundle)
        }

        return binding.root
    }

    private fun setInitData() {
        // To avoid creating and saving hardcoded data every time.
        val prefs = activity?.applicationContext?.getSharedPreferences("Flag_Preference", 0)
        val check = prefs?.getBoolean("FLAG", false)

        if (check == true) {
        } else {
            for (i in 0 until foodItems.size) {
                viewModel.upsert(foodItems[i])

                val prefs = activity?.applicationContext?.getSharedPreferences("Flag_Preference", 0)
                val editor = prefs?.edit()
                editor?.putBoolean("FLAG", true)
                editor?.apply()
            }
        }
    }
}