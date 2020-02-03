package com.example.dicodingexpert2.ui.nextmatch

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
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.databinding.FragmentNextMatchBinding
import com.example.dicodingexpert2.db.DicodingDb
import com.example.dicodingexpert2.model.EventFootball
import com.example.dicodingexpert2.ui.detailleague.DetailLeagueFragmentDirections
import com.example.dicodingexpert2.utils.ViewModelFactory

class NextMatchFragment : Fragment(), NextMatchAdapter.OnMatchClickListener {

    private lateinit var viewmodel: NextMatchViewmodel

    private lateinit var binding: FragmentNextMatchBinding

    private lateinit var adapter: NextMatchAdapter

    private lateinit var viewModelFactory : ViewModelProvider.Factory

    companion object{

        private var idLeague = ""

        fun newInstance(id: String): Fragment {
            idLeague = id
            return NextMatchFragment ()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val repository = DicodingDb.getInstance(activity!!.applicationContext)
        viewModelFactory = ViewModelFactory{NextMatchViewmodel(repository!!, Api.retrofitService)}
        viewmodel = ViewModelProvider(this, viewModelFactory).get(NextMatchViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_next_match, container, false)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.settingId(idLeague)
        initRecyclerView()
        viewListLeague()

        viewmodel.isMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        adapter = NextMatchAdapter(this)
        val layoutmanager = LinearLayoutManager(context)
        binding.rvMatchInfo.layoutManager = layoutmanager
        binding.rvMatchInfo.adapter = adapter
    }

    private fun viewListLeague() {
        viewmodel.data.observe(this, Observer {
            adapter.submitList(it.events)
        })
    }

    override fun onDetailMacthClick(data: EventFootball) {
        val action = DetailLeagueFragmentDirections.actionDetailMatcFragmentLaunch(data.idEvent)
        findNavController().navigate(action)
    }

    override fun onSaveMatchSelcted(data: EventFootball) {
        viewmodel.fetchLogoHomeTeam(data)
    }
}
