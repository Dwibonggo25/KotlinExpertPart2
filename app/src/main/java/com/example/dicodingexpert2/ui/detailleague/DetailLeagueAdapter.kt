package com.example.dicodingexpert2.ui.detailleague

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvDetailLeagueBinding
import com.example.dicodingexpert2.model.LeagueDetail

class DetailLeagueAdapter : ListAdapter<LeagueDetail, DetailLeagueAdapter.ViewHolder>(DiffCallback){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvDetailLeagueBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: RvDetailLeagueBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LeagueDetail) {
            binding.apply {
                data = item
                binding.executePendingBindings()
            }
        }
    }
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<LeagueDetail>() {
            override fun areItemsTheSame(oldItem: LeagueDetail, newItem: LeagueDetail): Boolean {
                return oldItem.idLeague == newItem.idLeague
            }

            override fun areContentsTheSame(oldItem: LeagueDetail, newItem: LeagueDetail): Boolean {
                return oldItem == newItem
            }
        }
    }

}