package com.example.playground.ui.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.playground.databinding.ItemListBinding
import com.example.playground.data.model.Post

/**
 * [PostListAdapter]
 * @use {var adapter = PostListAdapter(items){
 *                              funtionToPerform
 *                              }}
 * @param items List of Posts to inflate RecyclerView with
 * @param listener function to call when item is clicked
 */
class PostListAdapter
    : PagedListAdapter<Post, PostListAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        with(holder){
            post?.let { bind(it) }
        }
    }

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(private var binding: ItemListBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(repo : Post) = with(binding){
            repository = repo
            //root.setOnClickListener { listener(repo) }
        }
    }

}