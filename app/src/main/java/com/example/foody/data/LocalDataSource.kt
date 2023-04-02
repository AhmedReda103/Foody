package com.example.foody.data

import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.entities.FavouritesEntity
import com.example.foody.data.database.entities.RecipesEntity
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

     fun readFavouriteRecipes():Flow<List<FavouritesEntity>>{
        return recipesDao.readFavouriteRecipes()
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