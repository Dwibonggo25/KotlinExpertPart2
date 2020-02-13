package com.example.dicodingexpert2.ui.listteam

import android.app.LauncherActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.FragmentListTeamBinding
import com.example.dicodingexpert2.databinding.RvItemTeamBinding
import com.example.dicodingexpert2.model.ListTeam

class ListTeamAdapter (private var listener : OnClickTeamListener) : ListAdapter <ListTeam, ListTeamAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemTeamBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener)

    class ViewHolder (private var binding: RvItemTeamBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (item : ListTeam, listener: OnClickTeamListener) {
            binding.apply {
                team = item

                binding.clItemTeam.setOnClickListener {
                    listener.onItemTeamClick(item)
                }

                binding.ivFavorite.setOnClickListener {
                    listener.onItemFavoriteClick(item)
                }

                binding.executePendingBindings()

            }
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<ListTeam>() {
            override fun areItemsTheSame(oldItem: ListTeam, newItem: ListTeam): Boolean {
                return oldItem.idTeam == newItem.idTeam
            }

            override fun areContentsTheSame(oldItem: ListTeam, newItem: ListTeam): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnClickTeamListener {
        fun onItemTeamClick (data: ListTeam)
        fun onItemFavoriteClick (data: ListTeam)
    }
}