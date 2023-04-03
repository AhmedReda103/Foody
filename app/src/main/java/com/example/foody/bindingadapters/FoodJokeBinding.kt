package com.example.foody.bindingadapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.example.foody.data.database.entities.FoodJokeEntity
import com.example.foody.models.FoodJoke
import com.example.foody.utils.NetworkResult
import com.google.android.material.card.MaterialCardView

class FoodJokeBinding {


    companion object {

        @BindingAdapter("readApiResponse2", "readDatabase2", requireAll = false)
        @JvmStatic
        fun setCardAndProgressBarVisibility(
            view: View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?,
        ) {
            if (apiResponse != null) {
                when (apiResponse) {
                    is NetworkResult.Error -> {
                        when (view) {
                            is CardView -> {
                                view.visibility = View.VISIBLE
                                if (database != null) {
                                    if (database.isEmpty()) {
                                        view.visibility = View.INVISIBLE
                                    }
                                }
                            }
                            is ProgressBar -> {
                                view.visibility = View.INVISIBLE
                            }
                        }
                    }
                    is NetworkResult.Loading -> {
                        when (view) {
                            is CardView -> {
                                view.visibility = View.INVISIBLE
                            }
                            is ProgressBar -> {
                                view.visibility = View.VISIBLE
                            }
                        }
                    }
                    is NetworkResult.Success -> {
                        when (view) {
                            is ProgressBar -> {
                                view.visibility = View.INVISIBLE
                            }
                            is MaterialCardView -> {
                                view.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }


        @BindingAdapter("readApiResponse3", "readDatabase3", requireAll = true)
        @JvmStatic
        fun setErrorViewVisibility(
            view: View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {
            if (database!=null){
                if(database.isEmpty()){
                    view.visibility = View.VISIBLE
                    if(view is TextView){
                        if(apiResponse!=null){
                            view.text =apiResponse.message.toString()
                        }
                    }

                }
            }

            if(apiResponse is NetworkResult.Success){
                view.visibility=View.INVISIBLE
            }
        }


    }

}