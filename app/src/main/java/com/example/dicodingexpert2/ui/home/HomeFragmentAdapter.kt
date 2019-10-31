package com.example.dicodingexpert2.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvListLeagueBinding
import com.example.dicodingexpert2.model.League

class HomeFragmentAdapter : ListAdapter<League, HomeFragmentAdapter.ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvListLeagueBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: RvListLeagueBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClicklistener {
                binding.list?.let {league ->
                    navigateToDetail (league, it)
                }
            }
        }

        private fun navigateToDetail(league: League, it: View) {
            val action = HomeLeagueFragmentDirections.detailLeagueFragmentLaunch(league.idLeague.toInt())
            it.findNavController().navigate(action)
        }

        fun bind(item: League) {
            binding.apply {
                list = item
                binding.executePendingBindings()
            }
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<League>() {
            override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
                return oldItem.idLeague == newItem.idLeague
            }

            override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
                return oldItem == newItem
            }
        }
    }
}