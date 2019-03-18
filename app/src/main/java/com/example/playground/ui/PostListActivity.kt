package com.example.playground.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.playground.R
import com.example.playground.databinding.ActivityListBinding
import com.example.playground.model.Post

class PostListActivity: AppCompatActivity() {

    /**
     * Databinding can be intialized by wrapping xml into layout tag.
     * lateinit modifier allows us to have non-null variables waiting for initialization
     */
    lateinit var binding: ActivityListBinding
    var postListViewModel = PostListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        //val repository = Post(17, 12, "Nothing","Hello")

        //binding.repository = repository
        binding.viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        binding.executePendingBindings()

        /**
         * This will change repository body text after 2sec and 4sec

        Handler().postDelayed({repository.body="New Name"}, 2000)

        Handler().postDelayed({repository.body="Name"}, 4000)

        //apply allows to call multiple mwthod in one instance

        binding.apply {
            repositoryName.text = "Medium Android Repository Article"
            repositoryOwner.text = "Mladen Rakonjac"
            numberOfStarts.text = "1000 stars"

        }
         */
    }
}