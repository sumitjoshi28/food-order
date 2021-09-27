package com.sumit.foodorder.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumit.foodorder.data.model.FoodItems
import com.sumit.foodorder.data.repository.FoodItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodSelectViewModel(private val repository: FoodItemRepository) : ViewModel() {

    // Encapsulating the mutable live data so as not to be changed its value by any fragment.
    private val _totalCount = MutableLiveData<Int>()
    val totalCount: LiveData<Int>
        get() = _totalCount

    private val _totalP = MutableLiveData<Int>()
    val totalP: LiveData<Int>
        get() = _totalP

    // Methods for event clicks and views

    fun upsert(items: FoodItems) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(items)
    }

    fun delete(items: FoodItems) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(items)
    }

    fun getAllFoodItems() = repository.getAllFoodItems()

    fun update(items: FoodItems) = CoroutineScope(Dispatchers.Main).launch {
        repository.update(items)
    }

    // Calculating total count and total price of food.

    fun getCount(): LiveData<Int> {
        var count = 0
        CoroutineScope(Dispatchers.IO).launch {
            val allList = repository.getAllFood()

            for (i in allList.indices) {
                count += allList[i].count
                _totalCount.postValue(count)
            }
        }
        return _totalCount
    }

    fun getTotalPrice(): LiveData<Int> {
        var totalPrice = 0
        CoroutineScope(Dispatchers.IO).launch {
            val allList = repository.getAllFood()

            for (i in allList.indices) {
                totalPrice += allList[i].count * allList[i].price
                _totalP.postValue(totalPrice)
            }
        }
        return _totalP
    }
}