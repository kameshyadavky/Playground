package com.example.playground.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        val repository = Post(17, 12, "Nothing","Hello")

        binding.repository = repository
        binding.executePendingBindings()

        /*
        apply allows to call multiple mwthod in one instance

        binding.apply {
            repositoryName.text = "Medium Android Repository Article"
            repositoryOwner.text = "Mladen Rakonjac"
            numberOfStarts.text = "1000 stars"

        }
         */
    }
}