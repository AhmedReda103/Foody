package com.example.foody.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foody.utils.Constants
import com.example.foody.utils.Constants.QUERY_ADD_RECIPES_INFORMATION
import com.example.foody.utils.Constants.QUERY_API_KEY
import com.example.foody.utils.Constants.QUERY_DIET
import com.example.foody.utils.Constants.QUERY_FILL_INGREDIENTS
import com.example.foody.utils.Constants.QUERY_NUMBER
import com.example.foody.utils.Constants.QUERY_TYPE


class RecipesViewModel : ViewModel() {

    fun applyQueries():HashMap<String , String>{
        val queries :HashMap<String , String> = HashMap()

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = Constants.API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPES_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }



}