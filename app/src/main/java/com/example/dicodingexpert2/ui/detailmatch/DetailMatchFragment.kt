package com.example.dicodingexpert2.ui.detailmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentDetailMatchBinding
import com.example.dicodingexpert2.utils.Result

class DetailMatchFragment : Fragment() {

    private lateinit var viewModel : DetailMatchViewModel

    private lateinit var binding : FragmentDetailMatchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(activity!!).get(DetailMatchViewModel::class.java)
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
