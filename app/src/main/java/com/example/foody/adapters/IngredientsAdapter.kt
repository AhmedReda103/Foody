package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.databinding.IngredientsRowLayoutBinding
import com.example.foody.databinding.RecipesRowLayoutBinding
import com.example.foody.models.ExtendedIngredient
import com.example.foody.models.Result

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    var recipes = emptyList<Result>()

    inner class ViewHolder(private val binding: IngredientsRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredients : ExtendedIngredient) {
            binding.ingredients = ingredients
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(IngredientsRowLayoutBinding.inflate(layoutInflater , parent , false ))
    }


    private val diffCallback = object : DiffUtil.ItemCallback<ExtendedIngredient>(){
        override fun areItemsTheSame(oldItem: ExtendedIngredient, newItem: ExtendedIngredient): Boolean {
            return oldItem ==newItem
        }

        override fun areContentsTheSame(oldItem: ExtendedIngredient, newItem: ExtendedIngredient): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this , diffCallback)


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentIngredient = differ.currentList[position]
        holder.bind(currentIngredient)
    }



}