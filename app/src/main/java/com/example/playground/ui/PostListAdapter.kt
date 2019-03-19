package com.example.playground.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.playground.databinding.ItemListBinding
import com.example.playground.model.Post
import android.R.attr.data



class PostListAdapter(private var items: ArrayList<Post>,
                                    private var listener: OnItemClickListener)
    : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setData(newData: ArrayList<Post>) {
        items = newData
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Post, listener: OnItemClickListener?) {
            binding.repository = repo
            if (listener != null) {
                binding.root.setOnClickListener{ listener.onItemClick(layoutPosition)}
            }

            binding.executePendingBindings()
        }
    }

}