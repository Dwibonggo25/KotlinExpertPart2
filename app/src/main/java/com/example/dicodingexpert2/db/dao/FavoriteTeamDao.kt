package com.example.dicodingexpert2.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dicodingexpert2.db.entity.FavoriteTeam
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FavoriteTeamDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun  insertInFavorite (favorite : FavoriteTeam) : Single<Long>

    @Query ("Select * from favorite_team where id_league =:idLeague")
    fun fetchFavoriteTeam (idLeague: String) : Observable<List<FavoriteTeam>>

    @Query ( "Delete from FAVORITE_TEAM where id =:idTeam")
    fun deleteFavoriteTeam (idTeam: String)  : Completable
}