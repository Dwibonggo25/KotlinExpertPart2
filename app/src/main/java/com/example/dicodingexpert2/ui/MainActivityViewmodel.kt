package com.example.dicodingexpert2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.model.FootballLeague
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainActivityViewmodel : ViewModel() {

    private var mCompositeDisposable = CompositeDisposable()


    private val _isData = MutableLiveData<FootballLeague>()
    val isData: LiveData<FootballLeague>
        get() = _isData


    fun getAllLeague() {
        mCompositeDisposable.add(Api.retrofitService.getAllDataLeague()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data -> getDataSucess(data) },
                { error -> getDataFailed(error) }))
    }

    private fun getDataFailed(error: Throwable) {
        Log.e("eror", "$error")
    }

    private fun getDataSucess(data: FootballLeague) {
        _isData.value = data
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }
}

private operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    add(subscribe)
}