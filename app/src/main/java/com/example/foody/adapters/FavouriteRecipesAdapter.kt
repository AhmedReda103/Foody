package com.example.foody.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.R
import com.example.foody.data.database.entities.FavouritesEntity
import com.example.foody.databinding.FavouriteRecipesRowLayoutBinding
import com.example.foody.databinding.IngredientsRowLayoutBinding
import com.example.foody.models.ExtendedIngredient
import com.example.foody.models.Result
import com.example.foody.ui.fragments.favourites.FavouriteRecipesFragmentDirections

class FavouriteRecipesAdapter(private val requiredActivity : FragmentActivity) : RecyclerView.Adapter<FavouriteRecipesAdapter.ViewHolder>() , ActionMode.Callback {


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

        /**
         * Long OnClickListener
         */
        holder.binding.favouriteRecipesRowLayout.setOnLongClickListener {
            requiredActivity.startActionMode(this)
            true
        }

    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favourite_contextual_menu , menu)
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
       return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color:Int){
        requiredActivity.window.statusBarColor =
            ContextCompat.getColor(requiredActivity , color )
    }


}