package com.example.playground.ui.posts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.example.playground.R
import com.example.playground.databinding.FragmentPostListBinding
import com.example.playground.data.model.Post
import com.example.playground.util.observe
import com.example.playground.util.viewModelProvider
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


/**
 * A simple [DaggerFragment] subclass.
 * Can only be used if injection is done for this fragment via @ContributesAndroidInjector
 */
class PostListFragment : DaggerFragment()  {

    lateinit var binding: FragmentPostListBinding

    private val repositoryRecyclerViewAdapter by lazy {
        PostListAdapter()
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

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

        // extension function in [Extension] util.
        viewModel = viewModelProvider(viewModelFactory)
        viewModel.repositories.observe(this){
            updateAdapter(it)
        }

        binding.viewModel = this@PostListFragment.viewModel
        binding.repositoryRv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.fab?.setOnClickListener {
            viewModel.loadRxRepositories()
        }
    }

    private fun updateAdapter(list: PagedList<Post>){
        repositoryRecyclerViewAdapter.submitList(list)
    }

}
