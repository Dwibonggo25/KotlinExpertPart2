package com.example.dicodingexpert2.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api
import kotlinx.coroutines.Dispatchers

class HomeLeagueViewmodel : ViewModel() {

    val searchText = ObservableField<String>()

    val data = liveData (Dispatchers.IO) {
        val league = Api.retrofitService.getAllData()
        emit(league)
    }

    val search = liveData (Dispatchers.IO) {
        val league = Api.retrofitService.searchFile(searchText.get().toString())
        emit(league)
    }

    fun setText(text: String) {
        searchText.set(text)
    }
}
