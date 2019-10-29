package com.example.dicodingexpert2.api

import com.example.dicodingexpert2.model.FootballLeague
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET ("all_leagues.php")
    fun getAllDataLeague() :  Observable<FootballLeague>

    @GET ("all_leagues.php")
    fun getAllData() :  Observable<FootballLeague>
}