package com.example.dicodingexpert2.ui.matchinfo

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api

class MatchInfoViewmodel : ViewModel() {

    val idLeague = ObservableField<String>()

    val data = liveData {
        val event = Api.retrofitService.fetchEventLeague(idLeague.get()!!)
        emit(event)
    }

    fun settingId(id: String) {
        idLeague.set(id)
    }
}