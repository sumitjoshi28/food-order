package com.sumit.foodorder.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.sumit.foodorder.R
import com.sumit.foodorder.databinding.FragmentFoodCartBinding

class FoodCartFragment : Fragment() {

    private lateinit var binding:FragmentFoodCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_food_cart, container, false
        )

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        val args = arguments?.getString("price")
        binding.totalCost.text = "TOTAL COST Rs ${args}"
        return binding.root
    }
}