package com.example.dicodingexpert2.ui.previousmatch

import android.content.Context
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.db.DicodingDb
import com.example.dicodingexpert2.db.entity.Favorite
import com.example.dicodingexpert2.model.PreviousMatch
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PreviousMatchViewmodel (private val repository: DicodingDb, private val api: ApiService) : BaseViewmodel() {

    private var logoHomeTeam = ObservableField<String>()

    private var logoAwayTeam = ObservableField<String>()

    private var _isMessage = MutableLiveData<String>()
    val isMessage : LiveData <String>
        get() = _isMessage

    val id= ObservableField<String>()

    val data= liveData {
        val match = api.fetchPreviousMatch(id.get()!!)
        emit(match)
    }

    fun setIdLeague(idLeague: String){
        id.set(idLeague)
    }


    fun fetchLogoHomeTeam(event: PreviousMatch)  {
        mCompositeDisposable += api.fetchLogoTeam(event.idHomeTeam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    for (i in data.teams) {
                        logoHomeTeam.set(i.strTeamLogo)
                    }
                    fetchLogoAwayTeam(event)
                },
                {})
    }

    fun fetchLogoAwayTeam(event: PreviousMatch) {
        mCompositeDisposable += api.fetchLogoTeam(event.idAwayTeam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { data ->
                    for (i in data.teams) {
                        Log.e("Ini logo away team: ", i.strTeamLogo)
                        logoAwayTeam.set(i.strTeamLogo)
                    }
                    saveInfavoriteDatabase(event)
                },
                { error ->
                    Log.e("Ini logo error: ", error.toString())
                })
    }

    private fun saveInfavoriteDatabase(event: PreviousMatch) {
        val data = Favorite(event.idEvent, event.strHomeTeam, logoHomeTeam.get(), event.intHomeScore, event.strAwayTeam, logoAwayTeam.get(), event.intAwayScore, event.strLeague)
        mCompositeDisposable += repository!!.favoriteDao().insertToFavorite(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _isMessage.value = "Succes save in database" },
                { _isMessage.value = "Failed save in database"})
    }
    

}