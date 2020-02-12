package com.example.dicodingexpert2.ui.classment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.base.BaseViewmodel
import kotlinx.coroutines.Dispatchers

class ClassmentMatchViewmodel (private var api: ApiService) : BaseViewmodel() {

    private val idLiga = MutableLiveData <String>()

    val tableMatch = liveData (Dispatchers.IO){
            emit(getApiResult{api.fetchTableClassment(idLiga.value!!)})
    }

    fun setIdLiga (id: String) {
        idLiga.value = id
    }
}