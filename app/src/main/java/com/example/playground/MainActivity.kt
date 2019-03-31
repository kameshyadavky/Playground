package com.example.playground

import android.os.Bundle
import android.support.design.widget.NavigationView
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity

class MainActivity: DaggerAppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        //val drawerLayout : DrawerLayout? = findViewById(R.id.drawerLayout)

    }

    private fun setupNavMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.navigationView)
        sideNavView?.setupWithNavController(navController)
    }

    private fun setupActionBar(navController: NavController) {
        setupActionBarWithNavController(navController)
    }

}