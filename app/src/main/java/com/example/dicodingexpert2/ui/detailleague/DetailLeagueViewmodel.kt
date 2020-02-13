package com.example.dicodingexpert2.ui.detailleague

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.model.LeagueDetail
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers

class DetailLeagueViewmodel (private var api: ApiService) : BaseViewmodel() {

    val searchText = MutableLiveData<String>()

    var idLeague = MutableLiveData <String>()

    var detail = ObservableField <LeagueDetail> ()

    private val _isDetailLeague = MutableLiveData<com.example.dicodingexpert2.utils.Result<LeagueDetail>>()
    val isDetailLeague : LiveData <com.example.dicodingexpert2.utils.Result<LeagueDetail>>
        get() = _isDetailLeague

    val search = searchText.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            if (searchText.value.isNullOrEmpty()) {
                emit(null)
            } else {
                emit(getApiResult { api.searchTeam(searchText.value!!) })
            }
        }
    }

    fun getDetailLeague (id: String) {
        mCompositeDisposable += api.getDetailLeague(id)
            .doOnSubscribe { setResult(com.example.dicodingexpert2.utils.Result.Loading()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    for (i in data.leagues){
                        detail.set(i)
                        setResult(com.example.dicodingexpert2.utils.Result.Success(i))
                    }
                },
                {
                    Log.e("Message: ", "$it")
                })
    }

    fun setIdLeague (id: String){
        idLeague.value = id
    }

    private fun setResult (result: com.example.dicodingexpert2.utils.Result<LeagueDetail>) {
        _isDetailLeague.postValue(result)
    }

    fun setText(text: String) {
        searchText.value = text
    }

}

//    val test = liveData <DetailLeagueResponse>(context = viewModelScope.coroutineContext + Dispatchers.IO){
//        val dispos = emitSource(
//            api.fetchDetailLeague(idLeague.value!!).map {
//                Result.Loading (null)
//            }
//        )
//        try {
//            dispos.dispose()
//            emitSource(api.fetchDetailLeague(idLeague.value!!).map {
//                Result.Success (it)
//            })
//        }catch (e: Exception) {
//            emitSource(api.fetchDetailLeague(idLeague.value!!).map {
//                Result.Erorr (e)
//            })
//        }
//    }

//val league = idLeague.switchMap {
//        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
//            emit(getApiResult {api.fetchDetailLeague(idLeague.value!!)})
//        }
//    }