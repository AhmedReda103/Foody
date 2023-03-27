package com.example.foody.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.foody.utils.Constants
import com.example.foody.utils.Constants.DEFAULT_DIET_TYPE
import com.example.foody.utils.Constants.DEFAULT_MEAL_TYPE
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@ActivityRetainedScoped
class DataStoreRepository@Inject constructor(@ApplicationContext private val context : Context) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.PREFERENCES_NAME)


    private object PreferenceKeys{
        val selectedMealType = stringPreferencesKey(Constants.PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(Constants.PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(Constants.DEFAULT_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(Constants.PREFERENCES_DIET_TYPE_Id)
    }


    suspend fun saveMealAndDietType(
        mealType :String ,
        mealTypeId : Int ,
        dietType : String ,
        dietTypeId: Int
    ){
        context.dataStore.edit {preferences->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedDietType] = dietType
            preferences[PreferenceKeys.selectedMealTypeId] = mealTypeId
            preferences[PreferenceKeys.selectedDietTypeId] = dietTypeId
        }
    }

    val readMealAndDietType : Flow<MealAndDietType> = context.dataStore.data.catch {exception->
        if(exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { preferences->
        val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?:DEFAULT_MEAL_TYPE
        val selectedDietType = preferences[PreferenceKeys.selectedDietType] ?:DEFAULT_DIET_TYPE
        val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId] ?:0
        val selectedDietTypeId = preferences[PreferenceKeys.selectedDietTypeId] ?:0

        MealAndDietType(selectedMealType , selectedMealTypeId , selectedDietType , selectedDietTypeId)
    }


}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)