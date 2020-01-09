package com.example.dicodingexpert2.ui.home

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentHomeLegaueBinding
import com.example.dicodingexpert2.model.League
import com.example.dicodingexpert2.model.SearchResponse
import com.example.dicodingexpert2.utils.Result

class HomeLeagueFragment : Fragment(), HomeFragmentAdapter.OnClickListener {

    private lateinit var viewmodel: HomeLeagueViewmodel

    private lateinit var binding: FragmentHomeLegaueBinding

    private lateinit var adapter: HomeFragmentAdapter

    lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewmodel = ViewModelProviders.of(activity!!).get(HomeLeagueViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_legaue, container, false)
        binding.vm = viewmodel
        binding.executePendingBindings()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomeFragmentAdapter(this)

        initRecyclerView()
        viewListLeague()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        val searchItem: MenuItem = menu.findItem(R.id.searchMenu)
        searchView = searchItem.actionView as SearchView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.searchMenu -> {
                searchLeague()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun infoMatchNotFound() {
        binding.rvFootballLeague.visibility = View.VISIBLE
        binding.lvSearch.visibility = View.GONE
        Toast.makeText(activity, "Match tidak ditemukan", Toast.LENGTH_SHORT).show()
    }

    private fun searchLeague() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                viewmodel.setText(text!!)
                if (text.isNullOrEmpty()) {
                    infoMatchNotFound()
                    return false
                }
                viewLeague()
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                viewmodel.setText(text!!)
                if (!text.isNullOrEmpty()) {
                    viewLeague()
                    return false
                }

                infoMatchNotFound()
                return true
            }

        })
    }

    private fun initRecyclerView() {
        var layoutmanager = GridLayoutManager (activity, 2)
        binding.rvFootballLeague.layoutManager = layoutmanager
        binding.rvFootballLeague.adapter = adapter
    }

    private fun viewListLeague() {
        viewmodel.data.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    adapter.submitList(it.data.leagues)
                    binding.progres.visibility = View.GONE
                }
                is Result.Erorr -> {
                    binding.progres.visibility = View.GONE
                }
                is Result.Loading -> {
                    binding.progres.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onViewDetailLeague(data: League) {
        val action = HomeLeagueFragmentDirections.detailLeagueFragmentLaunch(data.idLeague.toInt())
        findNavController().navigate(action)
    }

    private fun noLeagueData () {
        val adapterSearch = SearchLeagueAdapter()
        adapterSearch.submitList(null)
    }

    private fun viewLeague() {
        val adapterSearch = SearchLeagueAdapter()
        binding.lvSearch.adapter = adapterSearch

        viewmodel.search.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    validateDataSearch(it)
                    adapterSearch.submitList(it.data.event)
                }
                is Result.Erorr -> {

                }
            }
        })
    }

    private fun validateDataSearch(it: Result.Success<SearchResponse>) {
        if (it.data.event.isNullOrEmpty()){
            binding.rvFootballLeague.visibility = View.VISIBLE
        }else {
            binding.lvSearch.visibility = View.VISIBLE
            binding.rvFootballLeague.visibility = View.GONE

        }
    }

}
