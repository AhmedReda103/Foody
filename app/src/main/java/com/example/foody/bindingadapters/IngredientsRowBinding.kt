package com.example.foody.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foody.R
import com.example.foody.utils.Constants.BASE_IMAGE_URL

class IngredientsRowBinding {

    companion object{

        @BindingAdapter("loadImageFromURLForIngredients")
        @JvmStatic
        fun  loadImageFromURLForIngredients(imageView: ImageView, imageUrl : String){
            imageView.load(BASE_IMAGE_URL + imageUrl){
                crossfade(600)
                error(R.drawable.ic_image_error)
            }
        }



    }


}