package com.example.dicodingexpert2.ui.detailleague

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.model.LeagueDetail
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailLeagueViewmodel : BaseViewmodel() {

    var idLeague = MutableLiveData <String>()

    var detail = ObservableField <LeagueDetail> ()

    private val _isDetailLeague = MutableLiveData<com.example.dicodingexpert2.utils.Result<LeagueDetail>>()
    val isDetailLeague : LiveData <com.example.dicodingexpert2.utils.Result<LeagueDetail>>
        get() = _isDetailLeague

    fun getDetailLeague () {

        mCompositeDisposable += Api.retrofitService.getDetailLeague(idLeague.value!!)
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

}

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

//val league = idLeague.switchMap {
//        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO){
//            emit(getApiResult {Api.retrofitService.fetchDetailLeague(idLeague.value!!)})
//        }
//    }