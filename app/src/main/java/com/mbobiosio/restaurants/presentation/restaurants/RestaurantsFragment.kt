package com.mbobiosio.restaurants.presentation.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mbobiosio.restaurants.databinding.FragmentRestaurantBinding
import com.mbobiosio.restaurants.util.Resource
import com.mbobiosio.restaurants.util.gone
import com.mbobiosio.restaurants.util.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@AndroidEntryPoint
class RestaurantsFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantBinding

    private val viewModel by viewModels<RestaurantViewModel>()
    private val restaurantAdapter by lazy {
        RestaurantAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaurantBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            swipeRefreshLayout.setOnRefreshListener {
                lifecycleScope.launch {
                    viewModel.getRestaurants()
                }
            }

            restaurantsList.apply {
                adapter = restaurantAdapter
            }

            viewModel.restaurants.observe(viewLifecycleOwner) { result ->
                restaurantAdapter.submitList(result.data)
                when (result) {
                    is Resource.Loading -> {
                        if (!swipeRefreshLayout.isRefreshing) {
                            progressBar.visible()
                        }
                        progressBar.isVisible = result.data.isNullOrEmpty()
                    }
                    is Resource.Success -> {
                        progressBar.gone()
                        swipeRefreshLayout.isRefreshing = false
                    }
                    is Resource.Error -> {
                        progressBar.gone()
                        textViewError.isVisible = result.data.isNullOrEmpty()
                        swipeRefreshLayout.isRefreshing = false
                    }
                }
            }
        }
    }
}
