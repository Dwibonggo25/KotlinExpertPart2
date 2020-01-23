package com.example.dicodingexpert2.ui.detailleague

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentDetailLeagueBinding
import com.example.dicodingexpert2.ui.favorite.FavoriteFragment
import com.example.dicodingexpert2.ui.nextmatch.NextMatchFragment
import com.example.dicodingexpert2.ui.previousmatch.PreviousMatchFragment
import com.example.dicodingexpert2.utils.Result
import com.google.android.material.tabs.TabLayoutMediator

class DetailLeagueFragment : Fragment() {

    private lateinit var viewmodel: DetailLeagueViewmodel

    private lateinit var binding: FragmentDetailLeagueBinding

    var idLeague : Int= 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewmodel = ViewModelProviders.of(activity!!).get(DetailLeagueViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_league, container, false)
        binding.vm = viewmodel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idLeague = DetailLeagueFragmentArgs.fromBundle(arguments).idLeague
        viewmodel.setIdLeague(idLeague.toString())
        viewmodel.getDetailLeague()

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
                2 -> tab.setText("Favorite Match")
            }
        }.attach()
    }

    class ViewPagerAdapter (fragment: Fragment, idLeague: String): FragmentStateAdapter(fragment) {

        var id = idLeague

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 ->  NextMatchFragment.newInstance(id)
                1 -> PreviousMatchFragment.newInstance(id)
                else -> FavoriteFragment.newInstance()
            }
        }
    }

}
