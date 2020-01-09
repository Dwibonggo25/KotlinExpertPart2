package com.example.dicodingexpert2.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.model.EventSearch
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers

class HomeLeagueViewmodel : BaseViewmodel(){

    val searchText = MutableLiveData<String>()

    val data = liveData (Dispatchers.IO) {
        val league = getApiResult { Api.retrofitService.getAllData() }
        emit(league)
    }

    val search = searchText.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
            if (searchText.value.isNullOrEmpty()){
                emit(null)
            }else {
                emit(getApiResult {Api.retrofitService.searchFile(searchText.value!!)})
            }
        }
    }

    fun setText(text: String) {
        searchText.value = text
    }
}
