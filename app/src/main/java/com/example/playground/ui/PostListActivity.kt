package com.example.playground.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.playground.R
import com.example.playground.databinding.ActivityListBinding
import com.example.playground.injection.ViewModelFactory
import com.example.playground.model.Post
import com.example.playground.repository.ProjectRepositoryLocal
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PostListActivity: DaggerAppCompatActivity(), PostListAdapter.OnItemClickListener {

    /**
     * Databinding can be intialized by wrapping xml into layout tag.
     * lateinit modifier allows us to have non-null variables waiting for initialization
     */
    lateinit var binding: ActivityListBinding
    private val repositoryRecyclerViewAdapter = PostListAdapter(arrayListOf(), this)
    @Inject lateinit var mainViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_list)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)

        //val repository = Post(17, 12, "Nothing","Hello")

        //binding.repository = repository

        val viewModel = ViewModelProviders.of(this, mainViewModelFactory).get(PostListViewModel::class.java)

        binding.viewModel = viewModel

        binding.executePendingBindings()

        binding.repositoryRv.layoutManager = LinearLayoutManager(this)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        viewModel.redundant.observe(this,
            Observer<ArrayList<Post>> {
                viewModel.isLoading.set(false)
                it?.let{ repositoryRecyclerViewAdapter.setData(it)}
            })




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


    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}