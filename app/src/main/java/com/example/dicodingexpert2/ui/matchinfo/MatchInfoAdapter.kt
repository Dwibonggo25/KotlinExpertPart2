package com.example.dicodingexpert2.ui.matchinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvMatchInfoBinding
import com.example.dicodingexpert2.model.EventFootball
import com.example.dicodingexpert2.model.League

class MatchInfoAdapter : ListAdapter <EventFootball, MatchInfoAdapter.ViewHolder> (DiffCallback){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvMatchInfoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder (private var binding: RvMatchInfoBinding ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EventFootball) {
            binding.apply {
                list = item
                binding.executePendingBindings()
            }
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<EventFootball>() {
            override fun areItemsTheSame(oldItem: EventFootball, newItem: EventFootball): Boolean {
                return oldItem.idLeague == newItem.idLeague
            }

            override fun areContentsTheSame(oldItem: EventFootball, newItem: EventFootball): Boolean {
                return oldItem == newItem
            }
        }
    }
}