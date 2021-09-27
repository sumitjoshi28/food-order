package com.sumit.foodorder.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sumit.foodorder.data.repository.FoodItemRepository

class FoodSelectViewModelFactory(private val repository: FoodItemRepository) :ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FoodSelectViewModel(repository) as T
    }
}