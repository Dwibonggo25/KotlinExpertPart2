package com.example.dicodingexpert2.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.db.DicodingDb
import com.example.dicodingexpert2.db.entity.Favorite
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoriteViewModel(application: Context) : BaseViewmodel() {

    private var repository: DicodingDb? = null

    private var _favorite = MutableLiveData<List<Favorite>>()
    val favorite: LiveData<List<Favorite>>
        get() = _favorite

    private var _isMessage = MutableLiveData<String>()
    val isMessage: LiveData<String>
        get() = _isMessage

    init {
        repository = DicodingDb.getInstance(application.applicationContext)
    }

    fun fetchAllFavorite() {
        mCompositeDisposable += repository!!.favoriteDao().fetchAllFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _favorite.value = it
            }
    }

    fun deleteFavoriteList(data: Favorite) {
        mCompositeDisposable += repository!!.favoriteDao().deleteFromFavorite(data.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _isMessage.value = "Success delete data"
            }
    }

}