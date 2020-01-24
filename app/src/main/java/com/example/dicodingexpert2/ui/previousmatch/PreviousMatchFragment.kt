package com.example.dicodingexpert2.ui.previousmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentPreviousMatchBinding
import com.example.dicodingexpert2.model.PreviousMatch
import com.example.dicodingexpert2.ui.detailleague.DetailLeagueFragmentDirections
import com.example.dicodingexpert2.utils.ViewModelFactory

class PreviousMatchFragment : Fragment(), PreviousMatchAdapter.OnClickPreviousListener {

    lateinit var viewmodel: PreviousMatchViewmodel
    lateinit var binding: FragmentPreviousMatchBinding

    lateinit var adapter: PreviousMatchAdapter

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        private var idLeague = ""
        fun newInstance(id: String) : Fragment {
            idLeague = id
            return PreviousMatchFragment ()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModelFactory = ViewModelFactory(activity!!)

        viewmodel= ViewModelProviders.of(this, viewModelFactory).get(PreviousMatchViewmodel::class.java)
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_previous_match, container, false)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.setIdLeague(idLeague)
        initRecyclerView()
        viewListLeague()

        viewmodel.isMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        adapter = PreviousMatchAdapter(this)
        val layoutmanager = LinearLayoutManager(context)
        binding.rvMatchInfo.layoutManager = layoutmanager
        binding.rvMatchInfo.adapter = adapter
    }

    private fun viewListLeague() {
        viewmodel.data.observe(this, Observer {
            adapter.submitList(it.events)
        })
    }

    override fun onMatchClick(data: PreviousMatch) {
        val action = DetailLeagueFragmentDirections.actionDetailMatcFragmentLaunch(data.idEvent)
        findNavController().navigate(action)
    }

    override fun onFavoriteClicked(data: PreviousMatch) {
        viewmodel.fetchLogoHomeTeam(data)
    }

}
