package com.example.dicodingexpert2.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.base.BaseViewmodel
import kotlinx.coroutines.Dispatchers

class HomeLeagueViewmodel (private val api: ApiService) : BaseViewmodel() {

    val searchText = MutableLiveData<String>()

    val data = liveData(Dispatchers.IO) {
        val league = getApiResult { api.getAllData() }
        emit(league)
    }

    val search = searchText.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            if (searchText.value.isNullOrEmpty()) {
                emit(null)
            } else {
                emit(getApiResult { api.searchFile(searchText.value!!) })
            }
        }
    }

    fun setText(text: String) {
        searchText.value = text
    }
}
