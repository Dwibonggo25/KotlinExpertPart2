package com.example.dicodingexpert2.api

import com.example.dicodingexpert2.model.DetailLeagueResponse
import com.example.dicodingexpert2.model.FootballLeague
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET ("all_leagues.php")
    suspend fun getAllData() :  FootballLeague

    @GET ("lookupleague.php")
    suspend fun fetchDetailLeague (@Query (value = "id") id: String ) : DetailLeagueResponse
}