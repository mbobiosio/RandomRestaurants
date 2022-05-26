package com.mbobiosio.restaurants.util

import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.google.android.material.imageview.ShapeableImageView

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@BindingAdapter("logo")
fun ShapeableImageView.logo(
    url: String?
) {
    url?.let {
        val imageLoader = context.imageLoader
        val request = ImageRequest.Builder(context)
            .data(it)
            .target(this)
            .memoryCachePolicy(CachePolicy.DISABLED)
            .diskCachePolicy(CachePolicy.DISABLED)
            .build()
        imageLoader.enqueue(request)
    }
}
