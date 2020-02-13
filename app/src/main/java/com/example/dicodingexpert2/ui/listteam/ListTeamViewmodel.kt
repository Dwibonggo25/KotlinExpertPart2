package com.example.dicodingexpert2.ui.listteam

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.db.DicodingDb
import com.example.dicodingexpert2.db.entity.FavoriteTeam
import com.example.dicodingexpert2.model.ListTeam
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListTeamViewmodel(private var api: ApiService, private var context: Context) : BaseViewmodel() {

    private val idLeague = MutableLiveData<String>()

    private var repository: DicodingDb? = null

    private var _isMessage = MutableLiveData<String>()
    val isMessage: LiveData<String>
        get() = _isMessage

    init {
        repository = DicodingDb.getInstance(context.applicationContext)
    }

    val listTeam = liveData {
        emit(getApiResult { api.fetchListTeam(idLeague.value!!) })
    }

    fun setIdLeague(id: String) {
        idLeague.value = id
    }

    fun insertFavoriteTeam(data: ListTeam) {
        mCompositeDisposable += repository!!.favoriteTeamDao()
            .insertInFavorite(FavoriteTeam(data.idTeam, data.idLeague, data.strTeam, data.strTeamLogo))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {_isMessage.value = "Success save data"},
                {_isMessage.value = "Failed save data"}
            )
    }
}