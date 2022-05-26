package com.mbobiosio.restaurants.data.source.repository

import androidx.room.withTransaction
import com.mbobiosio.restaurants.data.source.local.RestaurantDatabase
import com.mbobiosio.restaurants.data.source.remote.api.ApiService
import com.mbobiosio.restaurants.util.NetworkHelper
import com.mbobiosio.restaurants.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class RestaurantRepoImpl @Inject constructor(
    private val api: ApiService,
    private val db: RestaurantDatabase,
    private val networkHelper: NetworkHelper
) : RestaurantRepository {

    private val restaurantDao = db.restaurantDao()

    override suspend fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getAllRestaurants()
        },
        fetch = {
            delay(2000)
            api.getRestaurants()
        },
        saveFetchResult = { restaurants ->
            db.withTransaction {
                restaurantDao.deleteAllRestaurants()
                restaurantDao.insertRestaurants(restaurants)
            }
        },
        shouldFetch = {
            networkHelper.isNetworkAvailable()
        },
        onFetchFailed = {
        }
    )
}
