package com.example.dicodingexpert2.ui.detailmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentDetailMatchBinding
import com.example.dicodingexpert2.utils.Result
import com.example.dicodingexpert2.utils.ViewModelFactory

class DetailMatchFragment : Fragment() {

    private lateinit var viewModel : DetailMatchViewModel

    private lateinit var binding : FragmentDetailMatchBinding

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModelFactory = ViewModelFactory (activity!!)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(DetailMatchViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_match, container, false)
        binding.vm = viewModel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idEvent = DetailMatchFragmentArgs.fromBundle(arguments).idMatch
        viewModel.fetchDetailMatch(idEvent)

    }
}
