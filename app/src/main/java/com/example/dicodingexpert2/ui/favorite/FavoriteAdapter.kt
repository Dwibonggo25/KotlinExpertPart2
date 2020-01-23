package com.example.dicodingexpert2.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dicodingexpert2.databinding.RvItemFavoriteBinding
import com.example.dicodingexpert2.databinding.RvMatchInfoBinding
import com.example.dicodingexpert2.db.entity.Favorite

class FavoriteAdapter (private val listener: OnFavoriteClickListener) : ListAdapter <Favorite, FavoriteAdapter.ViewHolder> (DiffCallback){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, position: Int) : ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemFavoriteBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, listener)
    }

    class ViewHolder (private var binding: RvItemFavoriteBinding, private var listener: OnFavoriteClickListener ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Favorite) {

            binding.ivDelete.setOnClickListener {
                listener.onDeleteFavoriteSelected(item)
            }

            binding.clFavorite.setOnClickListener {
                listener.onDetailMacthClick(item)
            }

            binding.apply {
                list = item
                binding.executePendingBindings()
            }
        }
    }

    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Favorite>() {
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnFavoriteClickListener {
        fun onDetailMacthClick(data: Favorite)
        fun onDeleteFavoriteSelected(data: Favorite)
    }
}