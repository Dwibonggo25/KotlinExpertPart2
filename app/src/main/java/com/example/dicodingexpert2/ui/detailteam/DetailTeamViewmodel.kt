package com.example.dicodingexpert2.ui.detailteam

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.base.BaseViewmodel
import com.example.dicodingexpert2.model.DetailTeam
import com.example.dicodingexpert2.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DetailTeamViewmodel (private val api: ApiService) : BaseViewmodel() {

    val team = ObservableField <DetailTeam>()

    fun fetchDetailteam (id: String) {
        viewModelScope.launch {

        }
        mCompositeDisposable += api.fetchDetailTeam(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    for (i in it.teams) {
                        team.set(i)
                    }
                },
                {})
    }
}