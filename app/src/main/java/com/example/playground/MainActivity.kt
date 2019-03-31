package com.example.playground

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: DaggerAppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val drawerLayout : DrawerLayout? = findViewById(R.id.drawerLayout)
        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.postListFragment),
            drawerLayout)

        setupActionBar(navController, appBarConfiguration)

        setupNavigationMenu(navController)

    }


    private fun setupNavigationMenu(navController: NavController) {
        val sideNavView = findViewById<NavigationView>(R.id.navigationView)
        sideNavView?.setupWithNavController(navController)

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


    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        drawerLayout.closeDrawers()

        return if(item!= navigationView.checkedItem) item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
                    || super.onOptionsItemSelected(item)
        else
            false

    }*/
}