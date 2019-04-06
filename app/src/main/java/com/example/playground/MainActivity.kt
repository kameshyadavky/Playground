package com.example.playground

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*  //import for avoid using findViewById

class MainActivity: DaggerAppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController

        /**
         * AppBarConfiguration to define hamburger icon behaviour with navigation
         */
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.postListFragment),
            drawerLayout)

        setupActionBar(navController, appBarConfiguration)
        setupNavigationMenu(navController)

    }


    private fun setupNavigationMenu(navController: NavController) {
        navigationView?.setupWithNavController(navController)

        navigationView.setNavigationItemSelectedListener {
            if(it != navigationView.checkedItem){
                it.onNavDestinationSelected(navController)
            }
            it.isChecked = true
            drawerLayout?.closeDrawers()
            true
        }

    }

    private fun setupActionBar(navController: NavController,
                               appBarConfiguration: AppBarConfiguration) {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
    }
}