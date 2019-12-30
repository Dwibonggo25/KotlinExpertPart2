package com.example.dicodingexpert2.ui.previousmatch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvPreviousMatchBinding
import com.example.dicodingexpert2.model.PreviousMatch

class PreviousMatchAdapter (private var listener: OnClickPreviousListener): ListAdapter<PreviousMatch, PreviousMatchAdapter.ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvPreviousMatchBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, listener)
    }

    class ViewHolder (private var binding: RvPreviousMatchBinding, private var listener: OnClickPreviousListener): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PreviousMatch) {

            binding.clPrevious.setOnClickListener {
                listener.onMatchClick(item)
            }

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

    interface OnClickPreviousListener {
        fun onMatchClick (data: PreviousMatch)
    }
}