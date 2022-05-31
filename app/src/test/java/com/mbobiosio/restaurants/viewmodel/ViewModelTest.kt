package com.mbobiosio.restaurants.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mbobiosio.restaurants.MainCoroutineRule
import com.mbobiosio.restaurants.data.model.Restaurant
import com.mbobiosio.restaurants.data.source.repository.RestaurantRepository
import com.mbobiosio.restaurants.presentation.restaurants.RestaurantViewModel
import com.mbobiosio.restaurants.util.Resource
import com.mbobiosio.restaurants.util.getValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.* // ktlint-disable no-wildcard-imports
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {

    private val data = mutableListOf(
        Restaurant(
            "",
            "",
            "",
            ""
        )
    )

    private val responseBody = "".toResponseBody("text/plain".toMediaTypeOrNull())

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var testRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: RestaurantRepository

    private lateinit var viewModel: RestaurantViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = RestaurantViewModel(repository)
    }

    @After
    fun tearDown() {
        Mockito.reset(repository)
    }

    @Test
    fun `fetch restaurants`() = runBlocking {
        Mockito.`when`(repository.getRestaurants())
            .thenReturn(
                flowOf(Resource.Success(data))
            )

        viewModel.getRestaurants()
        verify(repository).getRestaurants()
        Assert.assertNotNull(getValue(viewModel.restaurants).data)
        Assert.assertEquals(
            getValue(viewModel.restaurants)::class.java,
            Resource.Success::class.java
        )
    }
}
