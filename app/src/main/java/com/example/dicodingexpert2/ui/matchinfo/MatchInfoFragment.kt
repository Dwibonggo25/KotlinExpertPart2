package com.example.dicodingexpert2.ui.matchinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentMatchInfoBinding
import com.example.dicodingexpert2.ui.home.HomeFragmentAdapter

class MatchInfoFragment : Fragment() {

    private lateinit var viewmodel: MatchInfoViewmodel

    private lateinit var binding: FragmentMatchInfoBinding

    private lateinit var adapter: MatchInfoAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProviders.of(this).get(MatchInfoViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_match_info, container, false)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewListLeague()
    }

    private fun initRecyclerView() {
        adapter = MatchInfoAdapter()
        val layoutmanager = LinearLayoutManager(context)
        binding.rvMatchInfo.layoutManager = layoutmanager
        binding.rvMatchInfo.adapter = adapter
    }

    private fun viewListLeague() {
        viewmodel.data.observe(this, Observer {
            adapter.submitList(it.events)
        })
    }
}
