package com.example.dicodingexpert2.ui.detailleague

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api

class DetailLeagueViewmodel : ViewModel() {

    val idLeague = ObservableField<String>()

    val detail = liveData {
        val data = Api.retrofitService.fetchDetailLeague(idLeague.get()!!)
        emit(data)
    }

    fun setIdLeague (id: Int){
        idLeague.set(id.toString())
    }

}