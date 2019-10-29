package com.example.dicodingexpert2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api
import kotlinx.coroutines.Dispatchers

class HomeLeagueViewmodel : ViewModel() {

    val data = liveData (Dispatchers.IO) {
        val league = Api.retrofitService.getAllData()
        emit(league)
    }
}
