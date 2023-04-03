package com.example.foody.ui.fragments.foodjoke

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.foody.MainViewModel
import com.example.foody.R
import com.example.foody.databinding.FragmentFoodJokeBinding
import com.example.foody.utils.Constants.API_KEY
import com.example.foody.utils.NetworkListener
import com.example.foody.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FoodJokeFragment : Fragment() {

    private var _binding :FragmentFoodJokeBinding ?=null
    private val binding get() = _binding!!

    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodJokeBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.mainViewModel = mainViewModel

        mainViewModel.getFoodJoke(API_KEY)
        mainViewModel.readFoodJokesResponse.observe(viewLifecycleOwner){response->

            when(response){
                is NetworkResult.Success->{
                    binding.foodJokeTextView.text = response.data?.text
                }
                is NetworkResult.Error -> {
                    loadFromCache()
                    Toast.makeText(requireContext() , response.message.toString() , Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    Log.d("FOOD_JOKE" , "Loading")
                }
            }

        }


        return binding.root
    }


    private fun loadFromCache(){
        lifecycleScope.launch {
            mainViewModel.readFoodJoke.observe(viewLifecycleOwner){database->
                if(!database.isNullOrEmpty())
                    binding.foodJokeTextView.text = database[0].foodJoke.text
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}





