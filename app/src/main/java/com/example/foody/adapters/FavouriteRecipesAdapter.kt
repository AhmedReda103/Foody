package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.data.database.entities.FavouritesEntity
import com.example.foody.databinding.FavouriteRecipesRowLayoutBinding
import com.example.foody.databinding.IngredientsRowLayoutBinding
import com.example.foody.models.ExtendedIngredient
import com.example.foody.models.Result
import com.example.foody.ui.fragments.favourites.FavouriteRecipesFragmentDirections

class FavouriteRecipesAdapter : RecyclerView.Adapter<FavouriteRecipesAdapter.ViewHolder>()  {


    inner class ViewHolder( val binding: FavouriteRecipesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favouritesEntity: FavouritesEntity ) {
            binding.favouritesEntity = favouritesEntity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(FavouriteRecipesRowLayoutBinding.inflate(layoutInflater , parent , false ))
    }


    private val diffCallback = object : DiffUtil.ItemCallback<FavouritesEntity>(){
        override fun areItemsTheSame(oldItem: FavouritesEntity, newItem: FavouritesEntity): Boolean {
            return oldItem ==newItem
        }

        override fun areContentsTheSame(oldItem: FavouritesEntity, newItem: FavouritesEntity): Boolean {
            return oldItem.result == newItem.result
        }
    }

    val differ = AsyncListDiffer(this , diffCallback)


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favouriteItem = differ.currentList[position]
        holder.bind(favouriteItem)

        /**
         * Single OnClickListener
         */
        holder.binding.favouriteRecipesRowLayout.setOnClickListener {
            val action = FavouriteRecipesFragmentDirections.actionFavouriteRecipesFragmentToDetailsActivity(favouriteItem.result)
            holder.itemView.findNavController().navigate(action)
        }

    }


}