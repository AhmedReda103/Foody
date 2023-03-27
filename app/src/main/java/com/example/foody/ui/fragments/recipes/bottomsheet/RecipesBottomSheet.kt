package com.example.foody.ui.fragments.recipes.bottomsheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.foody.databinding.RecipesBottomSheetBinding
import com.example.foody.utils.Constants
import com.example.foody.viewmodels.RecipesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*


class RecipesBottomSheet : BottomSheetDialogFragment() {

    private var _binding :RecipesBottomSheetBinding ?=null
    private val binding get() = _binding!!
    private val recipesViewModel : RecipesViewModel by activityViewModels()

    private var mealTypeChip = Constants.DEFAULT_MEAL_TYPE
    private var mealTypeChipId = 0
    private var dietTypeChip = Constants.DEFAULT_DIET_TYPE
    private var dietTypeChipId = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecipesBottomSheetBinding.inflate(inflater)

        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner) {
            mealTypeChip =it.selectedMealType
            dietTypeChip = it.selectedDietType
            updateChip(it.selectedMealTypeId , binding.mealTypeChipGroup )
            updateChip(it.selectedDietTypeId , binding.dietTypeChipGroup )
        }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.mealTypeChipGroup.setOnCheckedStateChangeListener{ group , selectedChipId ->
            val chip = group.findViewById<Chip>(group.checkedChipId)
            val selectedMealType = chip.text.toString().lowercase(Locale.ROOT)
            mealTypeChip = selectedMealType
            mealTypeChipId = group.checkedChipId
        }

        binding.dietTypeChipGroup.setOnCheckedStateChangeListener{ group , selectedChipId ->
            val chip = group.findViewById<Chip>(group.checkedChipId)
            val selectedDietType = chip.text.toString().lowercase(Locale.ROOT)
            dietTypeChip = selectedDietType
            dietTypeChipId = group.checkedChipId
        }

        binding.applyBtn.setOnClickListener {
            recipesViewModel.saveMealAndDietType(mealTypeChip , mealTypeChipId , dietTypeChip , dietTypeChipId)
            val action = RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment(true)
            findNavController().navigate(action)
        }
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if(chipId !=0){
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            }catch (e : Exception){
                Log.d("RecipesBottomSheet" , e.message.toString())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}