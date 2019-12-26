package com.example.dicodingexpert2.ui.detailleague

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.databinding.FragmentDetailLeagueBinding

class DetailLeagueFragment : Fragment() {

    private lateinit var viewmodel: DetailLeagueViewmodel

    private lateinit var binding: FragmentDetailLeagueBinding

    private lateinit var adapter: DetailLeagueAdapter

    var idLeague : Int= 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        viewmodel = ViewModelProviders.of(activity!!).get(DetailLeagueViewmodel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_league, container, false)
        binding.vm = viewmodel
        binding.executePendingBindings()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DetailLeagueAdapter()

        idLeague = DetailLeagueFragmentArgs.fromBundle(arguments).idLeague
        viewmodel.setIdLeague(idLeague.toString())
        viewmodel.getDetailLeague()
//        initRecyclerView()
       // viewLeague()
    }

//    private fun viewLeague() {
//        viewmodel.league.observe(this, Observer {
//            when (it){
//                is Result.Success -> {
//                    adapter.submitList(it.data.leagues)
//                    binding.progressBar.visibility = View.GONE
//                }
//                is Result.Erorr -> {
//                    Toast.makeText(activity, "Mohon periksa kembali internet anda", Toast.LENGTH_SHORT).show()
//                    binding.rvFootballLeague.visibility = View.GONE
//                    binding.progressBar.visibility = View.GONE
//                }
//                is Result.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.rvFootballLeague.visibility = View.GONE
//                }
//            }
//        })
//    }
//
//    private fun initRecyclerView() {
//        val layoutmanager = LinearLayoutManager(context)
//        binding.rvFootballLeague.layoutManager = layoutmanager
//        binding.rvFootballLeague.adapter = adapter
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId){
            R.id.match -> {
                matchFragmentLaunch()
            }
            else -> {
                previousFragmentLaunch()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun previousFragmentLaunch() {
        val action = DetailLeagueFragmentDirections.actionPreviousMatchLaunch(idLeague.toString())
        findNavController().navigate(action)
    }

    private fun matchFragmentLaunch() {
        val action= DetailLeagueFragmentDirections.actionMatchInfoFragmentLaunch(idLeague.toString())
        findNavController().navigate(action)
    }
}
