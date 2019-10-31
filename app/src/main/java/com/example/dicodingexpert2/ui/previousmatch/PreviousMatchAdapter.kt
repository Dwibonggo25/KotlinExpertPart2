package com.example.dicodingexpert2.ui.previousmatch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvPreviousMatchBinding
import com.example.dicodingexpert2.model.PreviousMatch

class PreviousMatchAdapter: ListAdapter<PreviousMatch, PreviousMatchAdapter.ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvPreviousMatchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder (private var binding: RvPreviousMatchBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PreviousMatch) {
            binding.apply {
                list = item
                binding.executePendingBindings()
            }
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<PreviousMatch>() {
            override fun areItemsTheSame(oldItem: PreviousMatch, newItem: PreviousMatch): Boolean {
                return oldItem.idLeague == newItem.idLeague
            }

            override fun areContentsTheSame(oldItem: PreviousMatch, newItem: PreviousMatch): Boolean {
                return oldItem == newItem
            }
        }
    }
}