package com.mbobiosio.restaurants.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Entity(tableName = "restaurants")
data class Restaurant(
    @PrimaryKey
    val name: String,
    val type: String,
    val logo: String,
    val address: String
)
