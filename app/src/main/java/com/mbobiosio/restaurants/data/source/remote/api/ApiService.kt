package com.mbobiosio.restaurants.data.source.remote.api

import com.mbobiosio.restaurants.data.model.Restaurant
import retrofit2.http.GET

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
interface ApiService {

    @GET("restaurant/random_restaurant?size=20")
    suspend fun getRestaurants(): List<Restaurant>
}
