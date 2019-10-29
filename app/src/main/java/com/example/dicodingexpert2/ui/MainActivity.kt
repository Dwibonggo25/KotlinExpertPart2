package com.example.dicodingexpert2.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewmodel

    lateinit var binding: ActivityMainBinding

    private lateinit var adapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()

        initRecyclerView ()
        viewListLeague()
    }

    private fun initRecyclerView() {
        adapter = MainActivityAdapter()
        val layoutmanager = LinearLayoutManager (this)
        binding.rvFootballLeague.layoutManager = layoutmanager
        binding.rvFootballLeague.adapter = adapter
    }

    private fun initBinding() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewmodel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = viewModel
        binding.executePendingBindings()
    }

    private fun viewListLeague() {
        viewModel.data.observe(this, Observer {
            adapter.submitList(it.leagues)
        })
    }

}
