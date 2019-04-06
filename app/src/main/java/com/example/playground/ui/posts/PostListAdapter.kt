package com.example.playground.ui.posts

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
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
class PostListAdapter(private var items: List<Post>,
                      private val listener: (Post) -> Unit
)
    : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun setData(newData: List<Post>) {
        items = newData
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ItemListBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(repo : Post, listener: (Post) -> Unit) = with(binding){
            repository = repo
            root.setOnClickListener { listener(repo) }
        }
    }

}