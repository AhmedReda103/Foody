package com.example.foody.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.adapters.FavouriteRecipesAdapter
import com.example.foody.data.database.entities.FavouritesEntity

class FavouriteRecipesBinding {

    companion object {


        @BindingAdapter("viewVisibility" , "setData" , requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            favouritesEntity: List<FavouritesEntity>?,
            mAdapter: FavouriteRecipesAdapter?
        ){
            if(favouritesEntity.isNullOrEmpty()){
                when(view){
                    is TextView->{
                        view.visibility = View.VISIBLE
                    }
                    is ImageView->{
                        view.visibility = View.VISIBLE
                    }
                    is RecyclerView->{
                        view.visibility = View.INVISIBLE
                    }
                }
            }else{
                when(view){
                    is TextView->{
                        view.visibility = View.INVISIBLE
                    }
                    is ImageView->{
                        view.visibility = View.INVISIBLE
                    }
                    is RecyclerView->{
                        view.visibility = View.VISIBLE
                        mAdapter?.differ?.submitList(favouritesEntity)
                    }
                }
            }
        }


    }

}