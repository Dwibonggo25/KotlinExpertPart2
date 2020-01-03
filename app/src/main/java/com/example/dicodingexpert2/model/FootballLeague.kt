package com.example.dicodingexpert2.model


import com.google.gson.annotations.SerializedName

data class FootballLeague(
    val leagues: List<League>
)

data class League(
    val idLeague: String,
    val strLeague: String,
    val legueLogo: Int
)
