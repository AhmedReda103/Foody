package com.example.foody.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foody.data.database.RecipesDatabase
import com.example.foody.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RecipesDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideDao(database :RecipesDatabase) = database.recipesDao()


}