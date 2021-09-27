package com.sumit.foodorder.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_items")
data class FoodItems(
    @ColumnInfo(name = "food_name")
    var name: String = "",
    @ColumnInfo(name = "food_price")
    var price: Int = 0,
    @ColumnInfo(name = "food_count")
    var count: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}