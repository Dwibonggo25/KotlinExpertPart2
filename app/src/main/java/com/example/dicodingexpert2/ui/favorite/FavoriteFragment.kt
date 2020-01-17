package com.example.dicodingexpert2.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding

    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        binding.executePendingBindings()
        return binding.root
    }
}
