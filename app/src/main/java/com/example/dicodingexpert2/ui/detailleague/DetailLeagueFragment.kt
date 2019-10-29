package com.example.dicodingexpert2.ui.detailleague

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentDetailLeagueBinding
import com.example.dicodingexpert2.databinding.FragmentHomeLegaueBinding
import com.example.dicodingexpert2.ui.home.HomeFragmentAdapter

class DetailLeagueFragment : Fragment() {

    private lateinit var viewmodel: DetailLeagueViewmodel

    private lateinit var binding: FragmentDetailLeagueBinding

    private lateinit var adapter: DetailLeagueAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewmodel = ViewModelProviders.of(activity!!).get(DetailLeagueViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_league, container, false)
        binding.vm = viewmodel
        binding.executePendingBindings()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idLeague = DetailLeagueFragmentArgs.fromBundle(arguments).idLeague
        viewmodel.setIdLeague(idLeague)
        initRecyclerView()
        viewLeague()
    }

    private fun viewLeague() {
        viewmodel.detail.observe(this, Observer {
            adapter.submitList(it.leagues)
        })
    }

    private fun initRecyclerView() {
        adapter = DetailLeagueAdapter()
        val layoutmanager = LinearLayoutManager(context)
        binding.rvFootballLeague.layoutManager = layoutmanager
        binding.rvFootballLeague.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId){
            R.id.match -> {
                matchFragmentLaunch()
            }
            else -> {
                previousFragmentLaunch()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun previousFragmentLaunch() {

    }

    private fun matchFragmentLaunch() {

    }
}
