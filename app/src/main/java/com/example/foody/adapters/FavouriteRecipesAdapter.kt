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
import com.example.foody.ui.fragments.favourites.FavouriteRecipesFragmentDirections

class FavouriteRecipesAdapter(private val requiredActivity: FragmentActivity) :
    RecyclerView.Adapter<FavouriteRecipesAdapter.ViewHolder>(), ActionMode.Callback {

    private val selectedRecipes = arrayListOf<FavouritesEntity>()
    private val myViewHolders = arrayListOf<ViewHolder>()
    private var multiSelection = false
    lateinit var actionMode: ActionMode

    inner class ViewHolder(val binding: FavouriteRecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favouritesEntity: FavouritesEntity) {
            binding.favouritesEntity = favouritesEntity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(FavouriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false))
    }


    private val diffCallback = object : DiffUtil.ItemCallback<FavouritesEntity>() {
        override fun areItemsTheSame(
            oldItem: FavouritesEntity,
            newItem: FavouritesEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FavouritesEntity,
            newItem: FavouritesEntity
        ): Boolean {
            return oldItem.result == newItem.result
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        myViewHolders.add(holder)
        val favouriteItem = differ.currentList[position]
        holder.bind(favouriteItem)

        /**
         * Single OnClickListener
         */
        holder.binding.favouriteRecipesRowLayout.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, favouriteItem)
            }else{
                val action =
                    FavouriteRecipesFragmentDirections.actionFavouriteRecipesFragmentToDetailsActivity(
                        favouriteItem.result
                    )
                holder.itemView.findNavController().navigate(action)
            }

        }

        /**
         * Long OnClickListener
         */
        holder.binding.favouriteRecipesRowLayout.setOnLongClickListener {
            if(!multiSelection){
                requiredActivity.startActionMode(this)
                multiSelection = true
                applySelection(holder , favouriteItem)
                true
            }else{
                multiSelection = false
                true
            }

        }

    }



    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favourite_contextual_menu, menu)
        actionMode = mode!!
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
        multiSelection = false
        selectedRecipes.clear()
        myViewHolders.forEach {
            changeRecipesStyle(it , R.color.cardBackgroundColor , R.color.strokeColor)
        }
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int) {
        requiredActivity.window.statusBarColor =
            ContextCompat.getColor(requiredActivity, color)
    }

    private fun applySelection(holder: ViewHolder, favouriteEntity: FavouritesEntity) {
        if (selectedRecipes.contains(favouriteEntity)) {
            selectedRecipes.remove(favouriteEntity)
            changeRecipesStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
            applyActionModeTitle()
        } else {
            selectedRecipes.add(favouriteEntity)
            changeRecipesStyle(holder , R.color.cardBackgroundLightColor , R.color.colorPrimary)
            applyActionModeTitle()
        }
    }

    private fun changeRecipesStyle(holder: ViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.binding.favouriteRecipesRowLayout.setBackgroundColor(
            ContextCompat.getColor(
                requiredActivity,
                backgroundColor
            )
        )
        holder.binding.favouriteRowCardView.strokeColor =
            ContextCompat.getColor(requiredActivity, strokeColor)
    }

    fun applyActionModeTitle(){
        when(selectedRecipes.size){
            0->{
                actionMode.finish()
            }
            1->{
                actionMode.title = "${selectedRecipes.size} item selected"
            }
            else->{
                actionMode.title = "${selectedRecipes.size} items selected"
            }
        }
    }


}