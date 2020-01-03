package com.example.dicodingexpert2.ui.detailmatch

import androidx.databinding.ObservableField
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.model.DetailMatchEvent
import com.example.dicodingexpert2.model.DetailMatchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailMatchViewModel : BaseViewmodel() {

    val detail = ObservableField <DetailMatchEvent>()

    fun fetchDetailMatch (id : String) {
        Api.retrofitService.fetchDetailMatch(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    for (i in it.events){
                        detail.set(i)
                    }
                },
                {})
    }
}