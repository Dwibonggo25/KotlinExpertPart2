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

class SearchLeagueAdapter: ListAdapter<EventSearch, SearchLeagueAdapter.ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemSearchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: RvItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {

//        init {
//            binding.setClicklistener {
//                binding.list?.let {league ->
//                    navigateToDetail (league, it)
//                }
//            }
//        }

        private fun navigateToDetail(league: EventSearch, it: View) {
            val action = HomeLeagueFragmentDirections.detailLeagueFragmentLaunch(league.idLeague.toInt())
            it.findNavController().navigate(action)
        }

        fun bind(item: EventSearch) {
            binding.apply {
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
}