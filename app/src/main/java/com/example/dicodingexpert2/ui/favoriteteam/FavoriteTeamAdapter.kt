package com.example.dicodingexpert2.ui.favoriteteam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvItemFavoriteTeamBinding
import com.example.dicodingexpert2.db.entity.Favorite
import com.example.dicodingexpert2.db.entity.FavoriteTeam

class FavoriteTeamAdapter (private val listener : OnClickFavorite) : ListAdapter <FavoriteTeam, FavoriteTeamAdapter.ViewHolder> (DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from (parent.context)
        val binding = RvItemFavoriteTeamBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder (private var binding: RvItemFavoriteTeamBinding, private var listener: OnClickFavorite) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FavoriteTeam) {
            binding.apply {

                clFavoriteTeam.setOnClickListener {
                    listener.onItemFavoriteClick(data.id)
                }

                ivDelete.setOnClickListener {
                    listener.onItemFavoriteDelete(data.id)
                }

                team = data
                binding.executePendingBindings()
            }
        }
    }

    interface OnClickFavorite {
        fun onItemFavoriteClick(id : String)
        fun onItemFavoriteDelete (id: String)
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<FavoriteTeam>() {
            override fun areItemsTheSame(oldItem: FavoriteTeam, newItem: FavoriteTeam): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: FavoriteTeam, newItem: FavoriteTeam): Boolean {
                return oldItem == newItem
            }
        }
    }
}