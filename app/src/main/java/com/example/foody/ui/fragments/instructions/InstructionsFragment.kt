package com.example.foody.ui.fragments.instructions

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.foody.R
import com.example.foody.databinding.FragmentIngredientsBinding
import com.example.foody.databinding.FragmentInstructionsBinding
import com.example.foody.models.Result
import com.example.foody.utils.Constants


class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(inflater)
        val args = arguments
        val myBundle : Result? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            args?.getParcelable(Constants.RECIPE_RESULT_KEY, Result::class.java)
        } else {
            args?.getParcelable(Constants.RECIPE_RESULT_KEY)
        }

        binding.instructionsWebView.webViewClient = object :WebViewClient(){}
        val websiteUrl: String? = myBundle!!.sourceUrl
        if(websiteUrl!=null)
        binding.instructionsWebView.loadUrl(websiteUrl)

        return binding.root
    }


}