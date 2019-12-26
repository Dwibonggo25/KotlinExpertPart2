package com.example.dicodingexpert2.ui.detailleague

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.model.DetailLeagueResponse
import com.example.dicodingexpert2.model.LeagueDetail
import com.example.dicodingexpert2.utils.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers

class DetailLeagueViewmodel : BaseViewmodel() {

    var idLeague = MutableLiveData <String>()

    var detail = ObservableField <LeagueDetail> ()
//    val test = liveData <DetailLeagueResponse>(context = viewModelScope.coroutineContext + Dispatchers.IO){
//        val dispos = emitSource(
//            Api.retrofitService.fetchDetailLeague(idLeague.value!!).map {
//                Result.Loading (null)
//            }
//        )
//        try {
//            dispos.dispose()
//            emitSource(Api.retrofitService.fetchDetailLeague(idLeague.value!!).map {
//                Result.Success (it)
//            })
//        }catch (e: Exception) {
//            emitSource(Api.retrofitService.fetchDetailLeague(idLeague.value!!).map {
//                Result.Erorr (e)
//            })
//        }
//    }
    val league = idLeague.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
            emit(getApiResult {Api.retrofitService.fetchDetailLeague(idLeague.value!!)})
        }
    }

    fun getDetailLeague () {
        Api.retrofitService.getDetailLeague(idLeague.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data->
                    for (i in data.leagues){
                        detail.set(i)
                    }
                },
                {})
    }

    fun setIdLeague (id: String){
        idLeague.value = id
    }

}