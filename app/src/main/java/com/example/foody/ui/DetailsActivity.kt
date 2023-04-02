package com.example.foody.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.foody.MainViewModel
import com.example.foody.R
import com.example.foody.adapters.PagerAdapter
import com.example.foody.data.database.entities.FavouritesEntity
import com.example.foody.databinding.ActivityDetailsBinding
import com.example.foody.ui.fragments.ingredients.IngredientsFragment
import com.example.foody.ui.fragments.instructions.InstructionsFragment
import com.example.foody.ui.fragments.overview.OverviewFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel by viewModels<MainViewModel>()
    private val TAG = "DetailsActivity"
    private var savedRecipeId = 0
    private var recipeSaved = false
    private lateinit var menuItem : MenuItem

    val fragments =
        arrayListOf<Fragment>(OverviewFragment(), IngredientsFragment(), InstructionsFragment())

    private val titles = arrayListOf<String>("Overview", "Ingredients", "Instructions")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initViewPager()
        initTabLayout()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favourite_recipes_menu , menu)
         menuItem = menu!!.findItem(R.id.save_to_favourite_menu)
        checkSavedRecipes(menuItem)
        return true
    }

    private fun checkSavedRecipes(menuItem: MenuItem?) {
        mainViewModel.readFavouriteRecipes.observe(this){favouriteEntities->
            try {
                for (savedRecipe in favouriteEntities){
                    if(savedRecipe.result.id == args.result.id){
                        changeMenuItemColor(menuItem!! ,R.color.yellow )
                        recipeSaved=true
                        savedRecipeId = savedRecipe.id
                    }
                }
            }catch (ex:Exception){
                Log.d(TAG, ex.message.toString())
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }else if (item.itemId == R.id.save_to_favourite_menu && !recipeSaved){
            saveToFavourite(item)
        }else if (item.itemId == R.id.save_to_favourite_menu && recipeSaved){
            removeFromFavourites(item)
        }

        return super.onOptionsItemSelected(item)

    }

    private fun saveToFavourite(item: MenuItem ) {
        val favouritesEntity = FavouritesEntity(0 , args.result)
        mainViewModel.insertFavouriteRecipe(favouritesEntity)
        changeMenuItemColor(item , R.color.yellow)
        showSnackBar("Recipe Saved")
        recipeSaved=true
    }

    private fun removeFromFavourites(item: MenuItem){
        val favouritesEntity = FavouritesEntity(savedRecipeId , args.result)
        mainViewModel.deleteFavouriteRecipe(favouritesEntity)
        changeMenuItemColor(item , R.color.white)
        showSnackBar("Removed From Favourites ")
        recipeSaved=false
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root , message , Snackbar.LENGTH_SHORT).setAction("Okay"){}.show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon?.setTint(ContextCompat.getColor(this , color))
    }

    private fun initViewPager() {
        val resultBundle = Bundle()
        resultBundle.putParcelable("recipeBundle", args.result)
        val pagerAdapter = PagerAdapter(this , resultBundle , fragments)
        binding.viewPager.adapter = pagerAdapter
    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        changeMenuItemColor(menuItem!! ,R.color.white )
    }
}