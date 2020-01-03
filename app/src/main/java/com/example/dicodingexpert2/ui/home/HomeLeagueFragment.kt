package com.example.dicodingexpert2.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
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

        adapter = HomeFragmentAdapter(this, context)

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
        binding.lvSearch.visibility = View.GONE
        binding.tvTitle.visibility = View.VISIBLE
        binding.rvFootballLeague.visibility = View.VISIBLE
        Toast.makeText(activity, "Match tidak ditemukan", Toast.LENGTH_SHORT).show()
    }

    private fun searchLeague() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    infoMatchNotFound()
                    return false
                }
                viewmodel.setText(text!!)
                viewLeague()
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    infoMatchNotFound()
                    return false
                }
                viewmodel.setText(text!!)
                viewLeague()
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
        adapter.submitList(resultList())
    }

    override fun onViewDetailLeague(data: League) {
        val action = HomeLeagueFragmentDirections.detailLeagueFragmentLaunch(data.idLeague.toInt())
        findNavController().navigate(action)
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
                    infoMatchNotFound()
                }
            }
        })
    }

    private fun validateDataSearch(it: Result.Success<SearchResponse>) {
        if (it.data.event.isNullOrEmpty()){
            infoMatchNotFound()
            binding.tvTitle.visibility = View.VISIBLE
            binding.rvFootballLeague.visibility = View.VISIBLE
            binding.lvSearch.visibility = View.GONE
        }else {
            binding.lvSearch.visibility = View.VISIBLE
            binding.tvTitle.visibility = View.GONE
            binding.rvFootballLeague.visibility = View.GONE
        }
    }

    private fun resultList () : List<League> {
        var list  = mutableListOf<League>()

        list.add(League("4331", "Bundes League", R.drawable.bundesliga))
        list.add(League("4328", "Premiere League", R.drawable.premiere_league))
        list.add(League("4332", "Seri A", R.drawable.seri_a))
        list.add(League("4335", "Spanish League", R.drawable.spanish))

        return list
    }
}
