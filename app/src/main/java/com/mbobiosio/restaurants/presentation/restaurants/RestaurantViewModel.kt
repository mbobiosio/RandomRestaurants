package com.mbobiosio.restaurants.presentation.restaurants

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbobiosio.restaurants.data.model.Restaurant
import com.mbobiosio.restaurants.data.source.repository.RestaurantRepository
import com.mbobiosio.restaurants.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: RestaurantRepository
) : ViewModel() {

    // val restaurants = repository.getRestaurants().asLiveData()
    val restaurants = MutableLiveData<Resource<List<Restaurant>>>()

    init {
        getRestaurants()
    }

    fun getRestaurants() = viewModelScope.launch {
        repository.getRestaurants().collect {
            restaurants.value = it
        }
    }
}
