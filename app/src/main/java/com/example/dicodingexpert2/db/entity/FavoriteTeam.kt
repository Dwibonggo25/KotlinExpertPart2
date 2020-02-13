package com.example.dicodingexpert2.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favorite_team")
data class FavoriteTeam (

    @PrimaryKey
    @ColumnInfo (name = "id")
    var id: String,

    @ColumnInfo (name = "id_league")
    var idLeague: String,

    @ColumnInfo (name = "name")
    var name: String,

    @ColumnInfo (name = "logo")
    var logo : String

)