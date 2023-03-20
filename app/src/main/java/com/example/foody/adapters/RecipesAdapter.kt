package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.databinding.RecipesRowLayoutBinding
import com.example.foody.models.Result

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    var recipes = emptyList<Result>()

    inner class ViewHolder(val binding: RecipesRowLayoutBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(result : Result) {
            binding.result = result
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(RecipesRowLayoutBinding.inflate(layoutInflater , parent , false ))
    }

    private val diffCallback = object :DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id ==newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this , diffCallback)


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecipe = differ.currentList[position]
        holder.bind(currentRecipe)
    }



}