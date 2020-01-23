package com.example.dicodingexpert2.ui.nextmatch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvMatchInfoBinding
import com.example.dicodingexpert2.model.EventFootball

class NextMatchAdapter (private val listener: OnMatchClickListener) : ListAdapter <EventFootball, NextMatchAdapter.ViewHolder> (DiffCallback){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvMatchInfoBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, listener)
    }

    class ViewHolder (private var binding: RvMatchInfoBinding, private var listener: OnMatchClickListener ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EventFootball) {

            binding.ivFavorite.setOnClickListener {
                listener.onSaveMatchSelcted(item)
            }

            binding.clNextMatch.setOnClickListener {
                listener.onDetailMacthClick(item)
            }

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

    interface OnMatchClickListener {
        fun onDetailMacthClick(data: EventFootball)
        fun onSaveMatchSelcted(data: EventFootball)
    }
}