package com.example.dicodingexpert2.ui.detailleague

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.databinding.FragmentDetailLeagueBinding
import com.example.dicodingexpert2.model.EventSearch
import com.example.dicodingexpert2.model.SearchTeam
import com.example.dicodingexpert2.ui.classment.ClassmentMatchFragment
import com.example.dicodingexpert2.ui.favoritematch.FavoriteFragment
import com.example.dicodingexpert2.ui.favoriteteam.FavoriteTeamFragment
import com.example.dicodingexpert2.ui.listteam.ListTeamFragment
import com.example.dicodingexpert2.ui.nextmatch.NextMatchFragment
import com.example.dicodingexpert2.ui.previousmatch.PreviousMatchFragment
import com.example.dicodingexpert2.utils.Result
import com.example.dicodingexpert2.utils.ViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class DetailLeagueFragment : Fragment() , SearchTeamAdapter.OnClickSearchTeam{

    private lateinit var viewmodel: DetailLeagueViewmodel

    private lateinit var binding: FragmentDetailLeagueBinding

    private lateinit var viewModelFactory : ViewModelProvider.Factory

    private lateinit var adapter : SearchTeamAdapter

    var idLeague : Int= 0

    lateinit var searchView: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)

        viewModelFactory = ViewModelFactory {DetailLeagueViewmodel(Api.retrofitService)}
        viewmodel = ViewModelProviders.of(activity!!, viewModelFactory).get(DetailLeagueViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_league, container, false)
        binding.vm = viewmodel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idLeague = DetailLeagueFragmentArgs.fromBundle(arguments).idLeague
        viewmodel.setIdLeague(idLeague.toString())
        viewmodel.getDetailLeague(idLeague.toString())

        viewmodel.isDetailLeague.observe(this, Observer {
            it?.let {
               when (it) {
                   is Result.Success -> {
                       setVisibleDataField()
                   }
                   is Result.Loading -> {
                       setHideDataField ()
                   }
               }
            }
        })
        settingViewPager()
        settingTablayout()

    }

    private fun setVisibleDataField() {
        binding.cardView.visibility = View.VISIBLE
        binding.viewPager.visibility = View.VISIBLE
        binding.tabLayout.visibility = View.VISIBLE
        binding.progressbar.visibility = View.GONE
    }

    private fun setHideDataField () {
        binding.cardView.visibility = View.GONE
        binding.viewPager.visibility = View.GONE
        binding.tabLayout.visibility = View.GONE
        binding.progressbar.visibility = View.VISIBLE
    }

    private fun settingViewPager() {
        val adapter = ViewPagerAdapter (this, idLeague.toString())
        binding.viewPager.adapter = adapter
    }

    private fun settingTablayout() {
        TabLayoutMediator (binding.tabLayout, binding.viewPager) {tab, position ->
            when (position) {
                0 -> tab.setText("Next Match")
                1 -> tab.setText("Preview Match")
                2 -> tab.setText("Classment")
                3 -> tab.setText("Team")
                4 -> tab.setText("Favorite Match")
                5 -> tab.setText("Favorite Team")
            }
        }.attach()
    }

    class ViewPagerAdapter (fragment: Fragment, idLeague: String): FragmentStateAdapter(fragment) {

        var id = idLeague

        override fun getItemCount(): Int = 6

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 ->  NextMatchFragment.newInstance(id)
                1 -> PreviousMatchFragment.newInstance(id)
                2 -> ClassmentMatchFragment.newInstance(id)
                3 -> ListTeamFragment.newInstance(id)
                4 -> FavoriteFragment.newInstance()
                else -> FavoriteTeamFragment.newInstance(id)
            }
        }
    }

    // To make search view

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

    private fun searchLeague() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    binding.rvSearchTeam.visibility = View.GONE
                    return false
                }
                viewmodel.setText(text)
                viewLeague()
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    binding.rvSearchTeam.visibility = View.GONE
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
                    if (it.data.teams.isNullOrEmpty()){
                        binding.rvSearchTeam.visibility = View.GONE
                    }else {
                        binding.rvSearchTeam.visibility = View.VISIBLE
                        transformData(it.data.teams)
                    }
                }
                is Result.Erorr -> {

                }
            }
        })
    }

    private fun transformData(teams: List<SearchTeam>) {

        adapter = SearchTeamAdapter(this)
        binding.rvSearchTeam.adapter = adapter

        val team = mutableListOf<SearchTeam>()
        for (i in teams){
            if (i.strSport.equals("Soccer")) {
                team.add(i)
            }
        }
        adapter.submitList(team)
    }

    override fun onItemSearchClick(idTeam: String) {
        val action = DetailLeagueFragmentDirections.actionDetailTeamLaunch()
        action.setTeamId(idTeam)
        findNavController().navigate(action)
    }
}
