package com.example.foody.utils

object Constants {

    const val BASE_URL = "https://api.spoonacular.com"
    const val API_KEY="004baf5eaa3746c189e5895942bf5bd2"


    //API QUERY KEYS
    const val QUERY_NUMBER = "number"
    const val QUERY_API_KEY = "apiKey"
    const val QUERY_TYPE = "type"
    const val QUERY_DIET = "diet"
    const val QUERY_ADD_RECIPES_INFORMATION = "addRecipeInformation"
    const val QUERY_FILL_INGREDIENTS = "fillIngredients"

    //Room Constants
    const val DATABASE_NAME = "recipes_database"
    const val RECIPES_TABLE = "recipes_table"
    const val FAVOURITE_RECIPES_TABLE = "favourite_recipes_table"
    const val FOOD_JOKES_TABLE = "food_jokes_table"

    //bottom sheet and datastore
    const val DEFAULT_MEAL_TYPE = "main course"
    const val DEFAULT_DIET_TYPE = "gluten free"
    const val DEFAULT_RECIPES_NUMBER = "50"

    const val PREFERENCES_MEAL_TYPE = "mealType"
    const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"

    const val PREFERENCES_DIET_TYPE = "dietType"
    const val PREFERENCES_DIET_TYPE_Id = "dietTypeId"

    const val PREFERENCES_NAME = "foody preferences "

    const val PREFERENCE_BACK_ONLINE = "backOnline"


}