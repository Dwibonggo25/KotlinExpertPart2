package com.example.dicodingexpert2.ui.nextmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentNextMatchBinding
import com.example.dicodingexpert2.model.EventFootball
import com.example.dicodingexpert2.ui.detailleague.DetailLeagueFragmentDirections

class NextMatchFragment : Fragment(), NextMatchAdapter.OnMatchClickListener {

    private lateinit var viewmodel: NextMatchViewmodel

    private lateinit var binding: FragmentNextMatchBinding

    private lateinit var adapter: NextMatchAdapter

    companion object{

        private var idLeague = ""

        fun newInstance(id: String): Fragment {
            idLeague = id
            return NextMatchFragment ()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProviders.of(this).get(NextMatchViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_next_match, container, false)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.settingId(idLeague)
        initRecyclerView()
        viewListLeague()
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

}
