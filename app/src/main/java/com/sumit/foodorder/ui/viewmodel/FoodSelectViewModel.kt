package com.sumit.foodorder.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumit.foodorder.data.model.FoodItems

class FoodSelectViewModel : ViewModel() {

    // set initial data for recycler view items. Hardcoding some data.
    private var foodItems: ArrayList<FoodItems> = arrayListOf(
        FoodItems("Chicken Tikka", 250, 0),
        FoodItems("Matar Paneer", 200, 0),
        FoodItems("Masala Dosa", 50, 0),
        FoodItems("Chicken Biryani", 200, 0),
        FoodItems("Veg Hakka Noodles", 150, 0),
        FoodItems("Egg Fried Rice", 150, 0),
    )

    // Encapsulating the mutable live data so as not to be changed its value by any fragment.
    private var _foodSelectList: MutableLiveData<ArrayList<FoodItems>> = MutableLiveData()
    val foodSelectList : LiveData<ArrayList<FoodItems>>
        get() = _foodSelectList

    init {
        _foodSelectList = MutableLiveData()
    }

    fun getRecyclerListObserver():MutableLiveData<ArrayList<FoodItems>>{
        return _foodSelectList
    }
    // Methods for button clicks
    fun addButtonClick(position: Int){
        foodItems[position].count = 1
        _foodSelectList.postValue(foodItems)
    }
    fun plusButtonClick(position: Int){
        foodItems[position].count.plus(1)
        _foodSelectList.postValue(foodItems)
    }
    fun minusButtonClick(position: Int){
        foodItems[position].count.minus(1)
        _foodSelectList.postValue(foodItems)
    }
}