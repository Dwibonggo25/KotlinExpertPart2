package com.example.dicodingexpert2.ui.classment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvItemClasmentBinding
import com.example.dicodingexpert2.model.Classment

class ClassmentMatchAdapter : ListAdapter<Classment, ClassmentMatchAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemClasmentBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder (private val binding : RvItemClasmentBinding) : RecyclerView.ViewHolder (binding.root) {

        fun bind (item: Classment) {
            binding.apply {
                table = item
                binding.executePendingBindings()
            }
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Classment>() {
            override fun areItemsTheSame(oldItem: Classment, newItem: Classment): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Classment, newItem: Classment): Boolean {
                return oldItem == newItem
            }
        }
    }
}