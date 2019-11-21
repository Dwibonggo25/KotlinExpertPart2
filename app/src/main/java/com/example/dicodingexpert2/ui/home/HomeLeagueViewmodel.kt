package com.example.dicodingexpert2.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.base.BaseViewmodel
import kotlinx.coroutines.Dispatchers

class HomeLeagueViewmodel : BaseViewmodel(){

    val searchText = ObservableField<String>()

    val data = liveData (Dispatchers.IO) {
        val league = getApiResult { Api.retrofitService.getAllData() }
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
