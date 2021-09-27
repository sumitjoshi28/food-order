package com.sumit.foodorder.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sumit.foodorder.data.dao.FoodItemDao
import com.sumit.foodorder.data.model.FoodItems

@Database(entities = [FoodItems::class], version = 1)
abstract class FoodItemDatabase : RoomDatabase() {
    abstract fun getFoodItemDao(): FoodItemDao

    companion object {
        @Volatile
        private var instance: FoodItemDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FoodItemDatabase::class.java,
            "FoodItemDB.db"
        ).build()
    }
}