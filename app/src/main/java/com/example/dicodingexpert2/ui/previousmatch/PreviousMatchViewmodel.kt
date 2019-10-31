package com.example.dicodingexpert2.ui.previousmatch

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api

class PreviousMatchViewmodel : ViewModel() {

    val id= ObservableField<String>()

    val data= liveData {
        val match = Api.retrofitService.fetchPreviousMatch(id.get()!!)
        emit(match)
    }

    fun setIdLeague(idLeague: String){
        id.set(idLeague)
    }
}