package com.example.dicodingexpert2.api

import androidx.lifecycle.LiveData
import com.example.dicodingexpert2.model.*
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET ("all_leagues.php")
    suspend fun getAllData() :  Response<FootballLeague>

    @GET ("lookupleague.php")
    suspend fun fetchDetailLeague (@Query (value = "id") id: String ) : Response <DetailLeagueResponse>

    @GET ("eventsnextleague.php")
    suspend fun fetchEventLeague (@Query ("id") id: String) : MatchInfoResponse

    @GET ("eventspastleague.php")
    suspend fun fetchPreviousMatch (@Query ("id") id: String) : PreviousMatchResponse

    @GET ("searchevents.php")
    suspend fun searchFile(@Query("e") e: String): Response <SearchResponse>

    @GET ("lookupleague.php")
    fun getDetailLeague (@Query (value = "id") id: String ) : Observable <DetailLeagueResponse>

    @GET ("lookupevent.php")
    fun fetchDetailMatch (@Query (value = "id") id: String) : Observable <DetailMatchResponse>

    @GET ("lookupteam.php")
    fun fetchLogoTeam (@Query(value = "id") id: String) : Observable <TeamLogoResponse>

    @GET ("lookuptable.php")
    suspend fun fetchTableClassment (@Query(value = "l") id: String) : Response <ClassmentResponse>

    @GET ("lookup_all_teams.php")
    suspend fun fetchListTeam (@Query(value = "id") id: String) : Response <ListTeamResponse>

    @GET ("lookupteam.php")
    fun fetchDetailTeam (@Query(value = "id") id: String) : Observable <DetailTeamResponse>
}