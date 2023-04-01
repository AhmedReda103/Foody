package com.example.foody.ui.fragments.ingredients

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.R
import com.example.foody.adapters.IngredientsAdapter
import com.example.foody.adapters.RecipesAdapter
import com.example.foody.databinding.FragmentIngredientsBinding
import com.example.foody.databinding.FragmentRecipesBinding
import com.example.foody.models.Result
import com.example.foody.utils.Constants.RECIPE_RESULT_KEY


class IngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null
    private val binding get() = _binding!!
    private val mAdapter by lazy { IngredientsAdapter() }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsBinding.inflate(inflater)
        setupRecyclerView()
        val args = arguments
        val myBundle : Result? = args?.getParcelable(RECIPE_RESULT_KEY , Result::class.java)

        myBundle?.extendedIngredients.let {
            mAdapter.differ.submitList(myBundle?.extendedIngredients)
        }

        return binding.root
    }


    private fun setupRecyclerView() {
        binding.ingredientsRecyclerView.adapter = mAdapter
        binding.ingredientsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


}