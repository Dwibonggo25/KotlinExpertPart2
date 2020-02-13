package com.example.dicodingexpert2.ui.favoriteteam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentFavoriteTeamBinding
import com.example.dicodingexpert2.ui.detailleague.DetailLeagueFragmentDirections
import com.example.dicodingexpert2.utils.ViewModelFactory

class FavoriteTeamFragment : Fragment(), FavoriteTeamAdapter.OnClickFavorite {

    private lateinit var binding : FragmentFavoriteTeamBinding

    private lateinit var viewmodel : FavoriteTeamViewmodel

    private lateinit var viewmodelFactory : ViewModelProvider.Factory

    private lateinit var adapter : FavoriteTeamAdapter

    companion object {
        private var idLeague = ""
        fun newInstance(id: String) : Fragment {
            idLeague = id
            return FavoriteTeamFragment ()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewmodelFactory = ViewModelFactory{FavoriteTeamViewmodel(activity!!)}
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_team, container, false)
        viewmodel = ViewModelProvider(activity!!, viewmodelFactory).get(FavoriteTeamViewmodel::class.java)

        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView ()

        viewmodel.fetchFavoriteteam(idLeague)

        viewmodel.isFavoriteTeam.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewmodel.isMessage.observe(this, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

    }

    private fun initRecyclerView() {
        adapter = FavoriteTeamAdapter(this)
        binding.rvFavoriteTeam.adapter = adapter
    }

    override fun onItemFavoriteClick(id: String) {
        val action = DetailLeagueFragmentDirections.actionDetailTeamLaunch()
        action.setTeamId(id)
        findNavController().navigate(action)
    }

    override fun onItemFavoriteDelete(id: String) {
        viewmodel.deleteFavoriteTeam(id)
    }
}
