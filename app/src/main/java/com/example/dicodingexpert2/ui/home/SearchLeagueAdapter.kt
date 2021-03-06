package com.example.dicodingexpert2.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvItemSearchBinding
import com.example.dicodingexpert2.model.EventSearch

class SearchLeagueAdapter (private var listener : OnSearchClickListener ) : ListAdapter<EventSearch, SearchLeagueAdapter.ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemSearchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, listener)
    }

    class ViewHolder(private val binding: RvItemSearchBinding, private val listener: OnSearchClickListener) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EventSearch) {
            binding.apply {

                binding.clSearhView.setOnClickListener {
                    listener.onSearchClicked(item.idEvent ?: "0")
                }

                list = item
                binding.executePendingBindings()
            }
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<EventSearch>() {
            override fun areItemsTheSame(oldItem: EventSearch, newItem: EventSearch): Boolean {
                return oldItem.idLeague == newItem.idLeague
            }

            override fun areContentsTheSame(oldItem: EventSearch, newItem: EventSearch): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnSearchClickListener {
        fun onSearchClicked (id: String)
    }
}