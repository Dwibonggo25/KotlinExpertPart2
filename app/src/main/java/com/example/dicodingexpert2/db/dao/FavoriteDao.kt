package com.example.dicodingexpert2.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.dicodingexpert2.db.entity.Favorite

@Dao
interface FavoriteDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertToFavorite (match: Favorite)
}