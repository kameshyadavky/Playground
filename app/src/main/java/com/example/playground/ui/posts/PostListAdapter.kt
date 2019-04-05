package com.example.playground.ui.posts

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.playground.databinding.ItemListBinding
import com.example.playground.model.Post


class PostListAdapter(private var items: List<Post>,
                                    private var listener: OnItemClickListener
)
    : androidx.recyclerview.widget.RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

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

    fun setData(newData: List<Post>) {
        items = newData
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ItemListBinding) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Post, listener: OnItemClickListener?) {
            binding.repository = repo
            if (listener != null) {
                binding.root.setOnClickListener{ listener.onItemClick(layoutPosition)}
            }

            binding.executePendingBindings()
        }
    }

}