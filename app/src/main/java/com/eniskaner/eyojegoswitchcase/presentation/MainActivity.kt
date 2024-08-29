package com.eniskaner.eyojegoswitchcase.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.eniskaner.eyojegoswitchcase.R
import com.eniskaner.eyojegoswitchcase.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.masNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        setupBottomNavigationBar()

    }

    private fun setupBottomNavigationBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.masNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        val fragmentTitles = mapOf(
            R.id.mainFragment to "main",
            R.id.switch1Fragment to "a",
            R.id.switch2Fragment to "b",
            R.id.switch3Fragment to "c",
            R.id.switch4Fragment to "d",
            R.id.switch5Fragment to "e"
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.toolbar.title = fragmentTitles[navController.currentDestination?.id]
        navController.addOnDestinationChangedListener{_, destination, _ ->
            binding.toolbar.title = fragmentTitles[destination.id]
            binding.toolbar.navigationIcon = null
        }
    }
}