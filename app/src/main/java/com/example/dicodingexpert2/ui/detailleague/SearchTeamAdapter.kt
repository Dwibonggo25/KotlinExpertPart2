package com.example.dicodingexpert2.ui.detailleague

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvItemSearchTeamBinding
import com.example.dicodingexpert2.model.SearchTeam

class SearchTeamAdapter (private val listener : OnClickSearchTeam) : ListAdapter<SearchTeam, SearchTeamAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemSearchTeamBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder (private val binding : RvItemSearchTeamBinding, private val listener: OnClickSearchTeam) : RecyclerView.ViewHolder (binding.root) {
        fun bind (data : SearchTeam) {
            binding.apply {
                clSearchTeam.setOnClickListener {
                    listener.onItemSearchClick(data.idTeam)
                }
                team = data
                executePendingBindings()
            }
        }
    }

    interface OnClickSearchTeam {
        fun onItemSearchClick(idTeam: String)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<SearchTeam>() {
            override fun areItemsTheSame(oldItem: SearchTeam, newItem: SearchTeam): Boolean {
                return oldItem.idTeam == newItem.idTeam
            }

            override fun areContentsTheSame(oldItem: SearchTeam, newItem: SearchTeam): Boolean {
                return oldItem == newItem
            }
        }
    }
}