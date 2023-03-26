package com.example.foody.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foody.R

class RecipesRowBinding {

    companion object{

        @BindingAdapter("loadImageFromURL")
        @JvmStatic
        fun  loadImageFromURL(imageView: ImageView , imageUrl : String){
            imageView.load(imageUrl){
                crossfade(600)
                error(R.drawable.ic_image_error)
            }
        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan : Boolean ){
            when(view){
                is TextView->{
                    view.setTextColor(ContextCompat.getColor(view.context , R.color.green))
                }
                is ImageView->{
                    view.setColorFilter(ContextCompat.getColor(view.context , R.color.green))
                }
            }
        }

    }


}