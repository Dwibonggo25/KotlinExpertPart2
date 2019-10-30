package com.example.dicodingexpert2.ui.matchinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api

class MatchInfoViewmodel : ViewModel() {

    val data = liveData {
        val event = Api.retrofitService.fetchEventLeague("4387")
        emit(event)
    }
}