package com.mbobiosio.restaurants.di

import android.content.Context
import androidx.room.Room
import com.mbobiosio.restaurants.data.source.local.RestaurantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): RestaurantDatabase =
        Room.databaseBuilder(
            context, RestaurantDatabase::class.java,
            "restaurant_db"
        )
            .fallbackToDestructiveMigration()
            .build()
}
