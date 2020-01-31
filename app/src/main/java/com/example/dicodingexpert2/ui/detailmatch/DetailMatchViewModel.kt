package com.example.dicodingexpert2.ui.detailmatch

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.model.DetailMatchEvent
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailMatchViewModel (private var apiService: ApiService): BaseViewmodel() {

    val detail = ObservableField <DetailMatchEvent>()

    val homeTeamLogo = ObservableField<String>()
    val awayTeamLogo = ObservableField<String>()

    val isLoading = ObservableBoolean ()

    fun fetchDetailMatch (id : String) {
        mCompositeDisposable += apiService.fetchDetailMatch(id)
            .doOnSubscribe { isLoading.set(true) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    isLoading.set(false)
                    for (i in it.events){
                        detail.set(i)
                    }
                    settingLogoForHomeTeam()
                    settingLogoForAwayTeam()
                },
                {})
    }

    private fun settingLogoForAwayTeam() {
        val id = detail.get()!!.idAwayTeam
        mCompositeDisposable += apiService.fetchLogoTeam(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data ->
                    for (i in data.teams){
                        awayTeamLogo.set(i.strTeamLogo)
                    }
                },
                {})
    }

    private fun settingLogoForHomeTeam() {
        val id = detail.get()!!.idHomeTeam
        mCompositeDisposable += apiService.fetchLogoTeam(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {data ->
                    for (i in data.teams){
                        homeTeamLogo.set(i.strTeamLogo)
                    }
                },
                {})
    }

}