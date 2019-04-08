package com.example.playground

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*  //import for avoid using findViewById


class MainActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bottom_app_bar.replaceMenu(R.menu.menu_nav_drawer)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        // for menu item listener
        bottom_app_bar.setNavigationOnClickListener {
            val bottomNavigationFragment = BottomNavigationFragment()
            bottomNavigationFragment.show(supportFragmentManager, bottomNavigationFragment.tag)
        }

        // for overflowing menu
        bottom_app_bar.setOnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.postListFragment -> {
                    navController.navigate(R.id.design_bottom_sheet)
                    true
                }
                else -> false
            }
        }

    }
}