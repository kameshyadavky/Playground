package com.example.playground.ui.navigation


import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.example.playground.R
import com.example.playground.util.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*
import kotlinx.android.synthetic.main.nav_header.view.*
import javax.inject.Inject

class BottomNavigationFragment : BottomSheetDialogFragment() {


    @Inject
    lateinit var auth: FirebaseAuth

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_navigation, container, false)
    }

    /**
     * This code is in onActivity created because navController will only be provided
     * if main activity's onCreate is complete.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val header = navigation_view.getHeaderView(0)
        header.name.text = auth.currentUser?.displayName
        header.email.text = auth.currentUser?.email

        header.profile_image.load(Uri.parse(auth.currentUser?.photoUrl.toString()))
        /*Glide.with(this)
            .load(Uri.parse(auth.currentUser?.photoUrl.toString()))
            .apply(
                RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.ic_person_black_24dp)
            )
            .into(object: CustomTarget<Drawable>(96,96){
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    header.email.setCompoundDrawablesRelativeWithIntrinsicBounds(resource,null,null,null)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    header.email.setCompoundDrawablesRelativeWithIntrinsicBounds(placeholder,null,null,null)
                }

            })*/
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