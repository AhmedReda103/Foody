package com.example.foody.ui.fragments.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.MainViewModel
import com.example.foody.R
import com.example.foody.adapters.FavouriteRecipesAdapter
import com.example.foody.adapters.IngredientsAdapter
import com.example.foody.adapters.RecipesAdapter
import com.example.foody.databinding.FragmentFavouriteRecipesBinding
import com.example.foody.databinding.FragmentIngredientsBinding
import com.example.foody.databinding.FragmentRecipesBinding
import com.example.foody.utils.Constants.API_KEY
import com.example.foody.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteRecipesFragment : Fragment() {

    private var _binding: FragmentFavouriteRecipesBinding? = null
    private val binding get() = _binding!!
    private val mAdapter by lazy { FavouriteRecipesAdapter() }
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteRecipesBinding.inflate(inflater , container , false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter
        setupRecyclerView(binding.favouriteRecipesRecyclerView)

        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
    }


}