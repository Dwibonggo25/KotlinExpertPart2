package com.example.dicodingexpert2.ui.detailteam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.databinding.FragmentDetailTeamBinding
import com.example.dicodingexpert2.utils.ViewModelFactory

class DetailTeamFragment : Fragment() {

    private lateinit var binding: FragmentDetailTeamBinding

    private lateinit var viewmodel: DetailTeamViewmodel

    private lateinit var viewmodelFactory : ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewmodelFactory = ViewModelFactory {DetailTeamViewmodel(Api.retrofitService)}
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_team, container, false)
        viewmodel = ViewModelProvider(activity!!, viewmodelFactory).get(DetailTeamViewmodel::class.java)
        binding.vm = viewmodel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idTeam = DetailTeamFragmentArgs.fromBundle(arguments).teamId

        viewmodel.fetchDetailteam(idTeam)

    }

}
