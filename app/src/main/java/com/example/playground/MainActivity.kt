package com.example.playground

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.playground.ui.navigation.BottomNavigationFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*  //import for avoid using findViewById


class MainActivity : DaggerAppCompatActivity(){

    private val bottomNavigationFragment by lazy {
        BottomNavigationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * must be provided before adding view
         * fullscreen can also be added in Theme but programmatically we can set flag whenever we want
         */
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val user = FirebaseAuth.getInstance().currentUser
        Log.e("user",user.toString())

        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        setSupportBottomBar(navController)

    }


    private fun setSupportBottomBar(navController: NavController) {

        //overflow menu will be inflated
        //currently setup in XML
        //bottom_app_bar.replaceMenu(R.menu.overflow_menu)

        // this is for overflow menu
        bottom_app_bar.setOnMenuItemClickListener {

            if(it.itemId == R.id.postListFragment) {
                bottomNavigationFragment.show(supportFragmentManager, bottomNavigationFragment.tag)
            }
            else{
                if (it.itemId != navController.currentDestination!!.id) {
                    it.onNavDestinationSelected(navController)
                }
                it.isChecked = true
            }
            true
        }

        // for menu item listener
        bottom_app_bar.setNavigationOnClickListener {
            bottomNavigationFragment.show(supportFragmentManager, bottomNavigationFragment.tag)
        }

    }

}