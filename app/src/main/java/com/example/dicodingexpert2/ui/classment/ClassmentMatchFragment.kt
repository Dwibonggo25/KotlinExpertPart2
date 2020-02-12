package com.example.dicodingexpert2.ui.classment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.databinding.FragmentClasemenMatchBinding
import com.example.dicodingexpert2.utils.Result
import com.example.dicodingexpert2.utils.ViewModelFactory

class ClassmentMatchFragment : Fragment() {

    private lateinit var viewmodel: ClassmentMatchViewmodel

    private lateinit var binding : FragmentClasemenMatchBinding

    private lateinit var adapter: ClassmentMatchAdapter

    private lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {

        private var idLeague = ""

        fun newInstance(id: String) : Fragment {
            idLeague = id
            return ClassmentMatchFragment ()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModelFactory = ViewModelFactory {ClassmentMatchViewmodel(Api.retrofitService)}
        viewmodel = ViewModelProvider(activity!!, viewModelFactory).get(ClassmentMatchViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_clasemen_match, container, false)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.setIdLiga(idLeague)

        initRecyclerView()
        viewmodel.tableMatch.observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    adapter.submitList(it.data.classment)
                }
                is Result.Loading -> {

                }
                is Result.Erorr -> {

                }
            }
        })
    }

    private fun initRecyclerView() {
        adapter = ClassmentMatchAdapter()
        val layoutmanager = LinearLayoutManager(context)
        binding.rvClassment.layoutManager = layoutmanager
        binding.rvClassment.adapter = adapter
    }


}
