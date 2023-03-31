package com.example.foody.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.data.DataStoreRepository
import com.example.foody.utils.Constants
import com.example.foody.utils.Constants.API_KEY
import com.example.foody.utils.Constants.DEFAULT_DIET_TYPE
import com.example.foody.utils.Constants.DEFAULT_MEAL_TYPE
import com.example.foody.utils.Constants.DEFAULT_RECIPES_NUMBER
import com.example.foody.utils.Constants.QUERY_ADD_RECIPES_INFORMATION
import com.example.foody.utils.Constants.QUERY_API_KEY
import com.example.foody.utils.Constants.QUERY_DIET
import com.example.foody.utils.Constants.QUERY_FILL_INGREDIENTS
import com.example.foody.utils.Constants.QUERY_NUMBER
import com.example.foody.utils.Constants.QUERY_SEARCH
import com.example.foody.utils.Constants.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application)  {

     val readMealAndDietType = dataStoreRepository.readMealAndDietType
     val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    var networkStatus = false
    var backOnline = false

    var mealType = DEFAULT_MEAL_TYPE
    var dietType = DEFAULT_DIET_TYPE



     fun saveMealAndDietType(
        mealType :String ,
        mealTypeId : Int ,
        dietType : String ,
        dietTypeId: Int
    ){
         viewModelScope.launch(Dispatchers.IO) {
             dataStoreRepository.saveMealAndDietType(mealType , mealTypeId, dietType, dietTypeId)
         }
    }

    private fun saveBackOnline(backOnline : Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }
    }

    fun applyQueries():HashMap<String , String>{
        viewModelScope.launch {
            readMealAndDietType.collectLatest {
                mealType = it.selectedMealType
                dietType = it.selectedDietType
            }
        }

        val queries :HashMap<String , String> = HashMap()
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPES_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    fun applySearchQueries(searchQuery : String):HashMap<String , String>{
        val queries :HashMap<String , String> = HashMap()
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPES_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        queries[QUERY_SEARCH]= searchQuery
        return queries
    }

    fun showNetworkStatus(){
        if(!networkStatus){
            Toast.makeText(getApplication() , "No Internet Connection" , Toast.LENGTH_LONG).show()
            saveBackOnline(true)
        }else if(networkStatus){
            if(backOnline){
                Toast.makeText(getApplication() , "Back Online" , Toast.LENGTH_LONG).show()
                saveBackOnline(false)
            }
        }
    }



}