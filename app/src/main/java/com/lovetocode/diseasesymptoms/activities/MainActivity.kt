package com.lovetocode.diseasesymptoms.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.lovetocode.diseasesymptoms.R
import com.lovetocode.diseasesymptoms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavWithNavFragment()
    }

    /*SETUP BOTTOM NAVIGATION VIEW WITH NAVIGATION FRAGMENT*/
    private fun setupBottomNavWithNavFragment()
    {
        navController = (supportFragmentManager.
        findFragmentById(R.id.nav_host_fragment) as NavHostFragment).
        navController

        binding.
        botttomNavigationView.
        setupWithNavController(navController)
    }

    public fun getNavController():NavController
    {
        return navController
    }
}