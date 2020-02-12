package com.example.dicodingexpert2.ui.listteam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.databinding.FragmentListTeamBinding
import com.example.dicodingexpert2.model.ListTeam
import com.example.dicodingexpert2.ui.detailleague.DetailLeagueFragmentDirections
import com.example.dicodingexpert2.utils.Result
import com.example.dicodingexpert2.utils.ViewModelFactory

class ListTeamFragment : Fragment(), ListTeamAdapter.OnClickTeamListener {

    private lateinit var binding : FragmentListTeamBinding

    private lateinit var viewModel : ListTeamViewmodel

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var adapter : ListTeamAdapter

    companion object {

        private var idLeague = ""

        fun newInstance(id: String) : Fragment {
            idLeague = id
            return ListTeamFragment ()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModelFactory = ViewModelFactory {ListTeamViewmodel (Api.retrofitService)}
        viewModel = ViewModelProvider(activity!!, viewModelFactory).get(ListTeamViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_team, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewModel.setIdLeague(idLeague)

        viewModel.listTeam.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    adapter.submitList(it.data.teams)
                    binding.progressBar.visibility = View.GONE
                }
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = ListTeamAdapter(this)
        binding.rvListTeam.adapter = adapter
    }

    override fun onItemTeamClick(data: ListTeam) {
        val action = DetailLeagueFragmentDirections.actionDetailTeamLaunch()
        action.setTeamId(data.idTeam)
        findNavController().navigate(action)
    }
}
