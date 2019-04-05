package com.example.playground.ui.posts

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.playground.databinding.FragmentPostListBinding
import com.example.playground.model.Post
import dagger.android.support.DaggerFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PostListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PostListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PostListFragment : DaggerFragment(),PostListAdapter.OnItemClickListener  {

    lateinit var binding: FragmentPostListBinding

    private val repositoryRecyclerViewAdapter = PostListAdapter(arrayListOf(), this)
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostListViewModel::class.java)
        binding.viewModel = this@PostListFragment.viewModel
        binding.repositoryRv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
        binding.repositoryRv.adapter = repositoryRecyclerViewAdapter
        viewModel.redundant.observe(this,
            Observer<List<Post>> {
                viewModel.isLoading.set(false)
                it?.let{ repositoryRecyclerViewAdapter.setData(it)}
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
