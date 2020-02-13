package com.example.dicodingexpert2.ui.favoriteteam

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.db.DicodingDb
import com.example.dicodingexpert2.db.entity.FavoriteTeam
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteTeamViewmodel(val context: Context) : BaseViewmodel() {

    private var repository: DicodingDb? = null

    private var _isMessage = MutableLiveData<String>()
    val isMessage: LiveData<String>
        get() = _isMessage

    private var _isFavoriteTeam = MutableLiveData<List<FavoriteTeam>>()
    val isFavoriteTeam: LiveData<List<FavoriteTeam>>
        get() = _isFavoriteTeam

    init {
        repository = DicodingDb.getInstance(context.applicationContext)
    }

    fun fetchFavoriteteam(idLeague: String) {
        mCompositeDisposable += repository!!.favoriteTeamDao().fetchFavoriteTeam(idLeague)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _isFavoriteTeam.value = it
            }
    }

    fun deleteFavoriteTeam (idTeam: String) {
        mCompositeDisposable += repository!!.favoriteTeamDao().deleteFavoriteTeam(idTeam)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                _isMessage.value = "Success delete team"
            }
    }
}