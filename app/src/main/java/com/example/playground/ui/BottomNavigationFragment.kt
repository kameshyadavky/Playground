package com.example.playground.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.playground.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class BottomNavigationFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
    }

    /**
     * This code is in onActivity created because navController will only be provided
     * if main activity's onCreate is complete.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val navController = findNavController()

        navigation_view.setupWithNavController(navController)
        navigation_view.setNavigationItemSelectedListener {
            if (it.itemId != navController.currentDestination!!.id) {
                it.onNavDestinationSelected(navController)
            }
            it.isChecked = true
            dismiss()
            true
        }
    }

}