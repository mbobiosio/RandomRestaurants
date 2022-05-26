package com.mbobiosio.restaurants.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mbobiosio.restaurants.data.model.Restaurant

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Database(entities = [Restaurant::class], version = 1)
abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
}
