package com.example.foody.data

import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.entities.FavouritesEntity
import com.example.foody.data.database.entities.FoodJokeEntity
import com.example.foody.data.database.entities.RecipesEntity
import com.example.foody.models.FoodJoke
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    suspend fun insertRecipes(recipesEntity: RecipesEntity){3
        recipesDao.insertRecipes(recipesEntity)
    }

     fun readRecipes() : Flow<List<RecipesEntity>>{
        return recipesDao.readRecipes()
    }

    suspend fun insertFoodJoke(foodJoke: FoodJokeEntity){
        recipesDao.insertFoodJoke(foodJoke)
    }

     fun readFavouriteRecipes():Flow<List<FavouritesEntity>>{
        return recipesDao.readFavouriteRecipes()
    }

    fun readFoodJoke():Flow<List<FoodJokeEntity>>{
        return recipesDao.readFoodJoke()
    }

    suspend fun insertFavouriteRecipes(favouritesEntity: FavouritesEntity){
        return recipesDao.insertFavouriteRecipes(favouritesEntity)
    }

    suspend fun deleteFavouriteRecipes(favouritesEntity: FavouritesEntity){
        return recipesDao.deleteFavouriteRecipe(favouritesEntity)
    }

    suspend fun deleteAllFavouriteRecipes(){
        return recipesDao.deleteAllFavouritesEntity()
    }


}