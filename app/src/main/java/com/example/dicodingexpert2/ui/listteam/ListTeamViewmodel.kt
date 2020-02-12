package com.example.dicodingexpert2.ui.listteam

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.base.BaseViewmodel

class ListTeamViewmodel(private var api: ApiService) : BaseViewmodel() {

    private val idLeague = MutableLiveData<String>()

    val listTeam = liveData {
        emit(getApiResult { api.fetchListTeam(idLeague.value!!) })
    }

    fun setIdLeague(id: String) {
        idLeague.value = id
    }
}