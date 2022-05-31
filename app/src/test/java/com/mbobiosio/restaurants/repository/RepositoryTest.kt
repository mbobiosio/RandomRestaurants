package com.mbobiosio.restaurants.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mbobiosio.restaurants.MainCoroutineRule
import com.mbobiosio.restaurants.data.source.local.RestaurantDatabase
import com.mbobiosio.restaurants.data.source.remote.api.ApiService
import com.mbobiosio.restaurants.data.source.repository.RestaurantRepoImpl
import com.mbobiosio.restaurants.util.NetworkHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.* // ktlint-disable no-wildcard-imports
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class RepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var testRule = MainCoroutineRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var database: RestaurantDatabase

    @Mock
    private lateinit var networkHelper: NetworkHelper

    private lateinit var repositoryImpl: RestaurantRepoImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repositoryImpl = RestaurantRepoImpl(apiService, database, networkHelper)
    }

    @After
    fun tearDown() {
        Mockito.reset(apiService)
        Mockito.reset(database)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `fetch restaurants`(): Unit = runBlocking {
        repositoryImpl.getRestaurants()
        Assert.assertNotNull(repositoryImpl.getRestaurants())
        // Mockito.verify(apiService).getRestaurants()
    }
}
