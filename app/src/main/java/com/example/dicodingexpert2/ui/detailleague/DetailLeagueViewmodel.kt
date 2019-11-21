package com.example.dicodingexpert2.ui.detailleague

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.*
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.model.DetailLeagueResponse
import kotlinx.coroutines.Dispatchers

class DetailLeagueViewmodel : BaseViewmodel() {

    var idLeague = MutableLiveData <String>()

    var detail = liveData (Dispatchers.Main) {
        emit(getApiResult {Api.retrofitService.fetchDetailLeague(idLeague.value!!)})
    }

    val league = idLeague.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
            emit(getApiResult {Api.retrofitService.fetchDetailLeague(idLeague.value!!)})
        }
    }

    fun setIdLeague (id: String){
        idLeague.value = id
    }

}