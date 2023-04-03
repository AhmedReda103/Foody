package com.example.foody.ui.fragments.favourites

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteRecipesFragment : Fragment()  , MenuProvider{

    private var _binding: FragmentFavouriteRecipesBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()
    private val mAdapter by lazy { FavouriteRecipesAdapter(requireActivity() , mainViewModel) }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteRecipesBinding.inflate(inflater , container , false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter
        setupRecyclerView(binding.favouriteRecipesRecyclerView)
        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL , false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mAdapter.clearContextualActionMode()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.favourite_recipes_menu , menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if(menuItem.itemId == R.id.delete_all_favourite_recipes_menu){
            mainViewModel.deleteAllFavouriteRecipe()
            showSnackBar("All Recipes Removed ")
        }
        return true
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .setAction("okay") {}.show()
    }
}