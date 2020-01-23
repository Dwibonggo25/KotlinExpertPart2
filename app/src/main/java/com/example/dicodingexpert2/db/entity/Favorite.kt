package com.example.dicodingexpert2.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favorite")
data class Favorite (

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "home_team")
    val homeTeam: String,

    @ColumnInfo(name = "logo_home_team")
    val logoHomeTeam: String?,

    @ColumnInfo(name = "score_home_team")
    val scoreHomeTeam: String?,

    @ColumnInfo(name = "away_team")
    val awayTeam: String?,

    @ColumnInfo(name = "logo_away_team")
    val logoAwayTeam: String?,

    @ColumnInfo(name = "score_away_team")
    val scoreAwayTeam: String?
)