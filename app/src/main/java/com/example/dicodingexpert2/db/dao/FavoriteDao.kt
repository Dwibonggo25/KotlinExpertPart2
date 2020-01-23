package com.example.dicodingexpert2.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dicodingexpert2.db.entity.Favorite
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface FavoriteDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertToFavorite (match: Favorite) : Single<Long>

    @Query ("SELECT * from favorite")
    fun fetchAllFavorite() : Observable<List<Favorite>>

    @Query ("DELETE from favorite WHERE id =:id")
    fun deleteFromFavorite(id: String) : Completable
}