package com.example.dicodingexpert2.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.databinding.FragmentHomeLegaueBinding
import com.example.dicodingexpert2.model.EventSearch
import com.example.dicodingexpert2.model.League
import com.example.dicodingexpert2.model.SearchResponse
import com.example.dicodingexpert2.utils.Result
import com.example.dicodingexpert2.utils.ViewModelFactory

class HomeLeagueFragment : Fragment(), HomeFragmentAdapter.OnClickListener, SearchLeagueAdapter.OnSearchClickListener {

    private lateinit var viewmodel: HomeLeagueViewmodel

    private lateinit var binding: FragmentHomeLegaueBinding

    private lateinit var adapter: HomeFragmentAdapter

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModelFactory = ViewModelFactory{HomeLeagueViewmodel(Api.retrofitService)}
        viewmodel = ViewModelProvider(activity!!).get(HomeLeagueViewmodel::class.java)
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
        Toast.makeText(activity, "Match tidak ditemukan", Toast.LENGTH_SHORT).show()
    }

    private fun searchLeague() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    return false
                }
                viewmodel.setText(text)
                viewLeague()
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    infoMatchNotFound()
                    return false
                }
                viewmodel.setText(text)
                viewLeague()
                return true
            }

        })
    }

    private fun viewLeague() {

        viewmodel.search.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    validateDataSearch(it)
                    if (it.data.event.isNullOrEmpty()){
                        infoMatchNotFound()
                    }else {
                        transformData(it.data.event)
                    }
                }
                is Result.Erorr -> {
                    infoMatchNotFound()
                }
            }
        })
    }

    private fun transformData(event: List<EventSearch>) {
        val adapterSearch = SearchLeagueAdapter(this)
        binding.lvSearch.adapter = adapterSearch

        val match = mutableListOf<EventSearch>()
        for (i in event){
            if (i.strSport.equals("Soccer")){
                match.add(EventSearch(i.dateEvent, i.dateEventLocal, i.idAwayTeam, i.idEvent, i.idHomeTeam, i.idLeague, i.idSoccerXML, i.intAwayScore, i.intAwayShots, i.intHomeScore,i.intHomeShots, i.intRound, i.intSpectators, i.strAwayFormation, i.strAwayGoalDetails, i.strAwayLineupDefense, i.strAwayLineupForward,
                    i.strAwayLineupGoalkeeper, i.strAwayLineupMidfield, i.strAwayLineupSubstitutes, i.strAwayRedCards, i.strAwayTeam, i.strAwayYellowCards, i.strBanner,
                     i.strCircuit, i.strCity, i.strCountry, i.strDate, i.strDescriptionEN, i.strEvent, i.strEventAlternate,i.strFanart, i.strFilename, i.strHomeFormation, i.strHomeGoalDetails,i.strHomeLineupDefense, i.strHomeLineupForward,i.strHomeLineupGoalkeeper,
                    i.strHomeLineupMidfield, i.strHomeLineupSubstitutes, i.strHomeRedCards, i.strHomeTeam, i.strHomeYellowCards, i.strLeague, i.strLocked, i.strMap, i.strPoster, i.strResult, i.strSeason, i.strSport))
            }
        }
        adapterSearch.submitList(match)
    }

    private fun validateDataSearch(it: Result.Success<SearchResponse>) {
        if (it.data.event.isNullOrEmpty()){
            infoMatchNotFound()
        }else {
            binding.lvSearch.visibility = View.VISIBLE
        }
    }

    private fun resultList () : List<League> {

        var list  = mutableListOf<League>()

        list.add(League("4331", "Bundes League", R.drawable.bundesliga))
        list.add(League("4328", "Premiere League", R.drawable.premiere_league))
        list.add(League("4332", "Seri A", R.drawable.seri_a))
        list.add(League("4335", "Spanish League", R.drawable.spanish))
        list.add(League("4356", "Australian A-League", R.drawable.australian_league))

        return list
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

    override fun onSearchClicked(id: String) {
        val action = HomeLeagueFragmentDirections.detailMatchFragmentLaunch(id)
        findNavController().navigate(action)
    }
}
