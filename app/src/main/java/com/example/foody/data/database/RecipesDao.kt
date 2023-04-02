package com.example.foody.data.database

import androidx.room.*
import com.example.foody.data.database.entities.FavouritesEntity
import com.example.foody.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteRecipes(favouriteEntity: FavouritesEntity)

    @Query("SELECT *from recipes_table order by id ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>

    @Query("SELECT *from favourite_recipes_table order by id ASC")
     fun readFavouriteRecipes():Flow<List<FavouritesEntity>>

    @Delete
    suspend fun deleteFavouriteRecipe(favouriteEntity: FavouritesEntity)

    @Query("DELETE FROM favourite_recipes_table")
    suspend fun deleteAllFavouritesEntity()


}