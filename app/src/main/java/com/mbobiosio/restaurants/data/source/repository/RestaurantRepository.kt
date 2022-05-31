package com.mbobiosio.restaurants.data.source.repository

import com.mbobiosio.restaurants.data.model.Restaurant
import com.mbobiosio.restaurants.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
interface RestaurantRepository {
    suspend fun getRestaurants(): Flow<Resource<List<Restaurant>>>
}
