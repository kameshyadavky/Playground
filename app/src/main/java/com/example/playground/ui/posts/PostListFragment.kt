package com.example.playground.ui.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.playground.R
import com.example.playground.databinding.FragmentPostListBinding
import com.example.playground.data.model.Post
import com.example.playground.util.observe
import com.example.playground.util.viewModelWithLiveData
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * A simple [DaggerFragment] subclass.
 * Can only be used if injection is done for this fragment via @ContributesAndroidInjector
 */
class PostListFragment : DaggerFragment()  {

    lateinit var binding: FragmentPostListBinding

    private lateinit var repositoryRecyclerViewAdapter :PostListAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: PostListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostListBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = this@PostListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositoryRecyclerViewAdapter = PostListAdapter(arrayListOf()){
            Toast.makeText(this.context,it.title.toString(),Toast.LENGTH_SHORT).show()
            NavHostFragment.findNavController(this).navigate(R.id.postListFragment)
        }

        // extension function in [Extension] util.
        viewModel = viewModelWithLiveData(viewModelFactory){
            observe(repositories,::updateAdapter)
        }
        binding.viewModel = this@PostListFragment.viewModel
        binding.repositoryRv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter

        /**
         * Extension method described in util.extension class
        /* *//*
        observe(viewModel.redundant){
            viewModel.isLoading.set(false)
            it?.let{repositoryRecyclerViewAdapter.setData(it)}
        }*/
       /* viewModel.redundant.observe(this
        ) {
            viewModel.isLoading.set(false)
            it?.let{repositoryRecyclerViewAdapter.setData(it)}}
        *//*viewModel.redundant.observe(this,
            Observer<List<Post>> {
                viewModel.isLoading.set(false)
                it?.let{ repositoryRecyclerViewAdapter.setData(it)}
            })*/*/

    }

    private fun updateAdapter(list : List<Post>){
        repositoryRecyclerViewAdapter.setData(list)
    }

}
