package com.example.dicodingexpert2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.databinding.ActivityMainBinding
import com.example.dicodingexpert2.ui.home.HomeFragmentAdapter
import com.example.dicodingexpert2.ui.home.HomeLeagueViewmodel
import com.example.dicodingexpert2.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeLeagueViewmodel

    lateinit var binding: ActivityMainBinding

    private lateinit var viewModelFactory : ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

        val host = NavHostFragment.create(R.navigation.navigation)
        supportFragmentManager.beginTransaction().replace(R.id.main_nav_fragment, host).setPrimaryNavigationFragment(host).commit()
    }


    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.container).navigateUp()

    private fun initBinding() {

        viewModelFactory = ViewModelFactory {HomeLeagueViewmodel(Api.retrofitService)}

        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeLeagueViewmodel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.executePendingBindings()
    }

}

