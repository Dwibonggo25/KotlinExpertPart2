package com.example.dicodingexpert2.ui.favorite

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
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentFavoriteBinding
import com.example.dicodingexpert2.db.entity.Favorite
import com.example.dicodingexpert2.ui.detailleague.DetailLeagueFragmentDirections
import com.example.dicodingexpert2.ui.previousmatch.PreviousMatchFragment
import com.example.dicodingexpert2.utils.ViewModelFactory

class FavoriteFragment : Fragment(), FavoriteAdapter.OnFavoriteClickListener {

    private lateinit var binding : FragmentFavoriteBinding

    private lateinit var viewModel: FavoriteViewModel

    private lateinit var viewModelFactory : ViewModelProvider.Factory

    private lateinit var adapter : FavoriteAdapter

    companion object {
        fun newInstance() : Fragment {
            return FavoriteFragment ()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModelFactory = ViewModelFactory{FavoriteViewModel(activity!!)}
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoriteViewModel::class.java)
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchAllFavorite()

        initRecyclerView()

        viewModel.favorite.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.isMessage.observe(this, Observer {
            it?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initRecyclerView() {
        adapter = FavoriteAdapter(this)
        binding.rvFavoriteMatch.adapter = adapter
    }

    override fun onDetailMacthClick(data: Favorite) {
        val action = DetailLeagueFragmentDirections.actionDetailMatcFragmentLaunch(data.id)
        findNavController().navigate(action)
    }

    override fun onDeleteFavoriteSelected(data: Favorite) {
        viewModel.deleteFavoriteList(data)
    }

}
