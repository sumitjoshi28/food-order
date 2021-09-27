package com.sumit.foodorder.data.repository

import com.sumit.foodorder.data.db.FoodItemDatabase
import com.sumit.foodorder.data.model.FoodItems

class FoodItemRepository(private val db: FoodItemDatabase) {
    suspend fun upsert(item: FoodItems) = db.getFoodItemDao().upsert(item)

    suspend fun delete(item: FoodItems) = db.getFoodItemDao().delete(item)

    fun getAllFoodItems() = db.getFoodItemDao().getAllFoodItems()

    fun getAllFood() = db.getFoodItemDao().getAllFood()

    suspend fun update(item: FoodItems) = db.getFoodItemDao().update(item)
}