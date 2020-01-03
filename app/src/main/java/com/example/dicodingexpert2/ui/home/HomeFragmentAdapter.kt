package com.example.dicodingexpert2.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvListLeagueBinding
import com.example.dicodingexpert2.model.League
import android.R.id
import android.content.Context
import android.graphics.drawable.Drawable



class HomeFragmentAdapter (private val listener: OnClickListener, private val context: Context?): ListAdapter<League, HomeFragmentAdapter.ViewHolder>(DiffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position), listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvListLeagueBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, context!!)
    }

    class ViewHolder(private val binding: RvListLeagueBinding, private val context: Context ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: League, listener: OnClickListener) {
            binding.clLeague.setOnClickListener {
                listener.onViewDetailLeague(data)
            }
            binding.apply {
                list = data
                binding.ivLeagueLogo.setImageDrawable(context.getDrawable(list!!.legueLogo))
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

    interface OnClickListener {
        fun onViewDetailLeague(data: League)
    }
}