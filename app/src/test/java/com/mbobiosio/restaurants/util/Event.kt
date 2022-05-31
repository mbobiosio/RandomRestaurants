package com.mbobiosio.restaurants.util

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
open class Event<out T>(val data: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            data
        }
    }

    fun peekContent() = data
}
