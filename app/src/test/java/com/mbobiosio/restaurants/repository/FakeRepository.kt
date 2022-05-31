package com.mbobiosio.restaurants.repository

import com.mbobiosio.restaurants.data.model.Restaurant
import com.mbobiosio.restaurants.data.source.repository.RestaurantRepository
import com.mbobiosio.restaurants.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class FakeRepository : RestaurantRepository {

    private val mockRestaurant = mutableListOf(
        Restaurant(
            "name1",
            "type1",
            "logo1",
            "address1"
        ),
        Restaurant(
            "name2",
            "type2",
            "logo2",
            "address2"
        )
    )

    private val result: Resource<List<Restaurant>> =
        Resource.Success(mockRestaurant)

    override fun getRestaurants(): Flow<Resource<List<Restaurant>>> = flow {
        this.emit(result)
    }
}
