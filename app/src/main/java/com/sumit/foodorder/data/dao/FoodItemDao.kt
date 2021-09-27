package com.sumit.foodorder.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sumit.foodorder.data.model.FoodItems

@Dao
interface FoodItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item:FoodItems)

    @Delete
    suspend fun delete(item: FoodItems)

    @Query("SELECT * FROM food_items")
    fun getAllFoodItems() : LiveData<List<FoodItems>>
}