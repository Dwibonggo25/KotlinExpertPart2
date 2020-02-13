package com.example.dicodingexpert2.model


import com.google.gson.annotations.SerializedName

data class SearchTeamResponse(
    @SerializedName("teams")
    val teams: List<SearchTeam>
)

data class SearchTeam(
    @SerializedName("idLeague")
    val idLeague: String,
    @SerializedName("idTeam")
    val idTeam: String,
    @SerializedName("intFormedYear")
    val intFormedYear: String,
    @SerializedName("strCountry")
    val strCountry: String,
    @SerializedName("strLeague")
    val strLeague: String,
    @SerializedName("strSport")
    val strSport: String,
    @SerializedName("strStadium")
    val strStadium: String,
    @SerializedName("strStadiumLocation")
    val strStadiumLocation: String,
    @SerializedName("strTeam")
    val strTeam: String,
    @SerializedName("strTeamLogo")
    val strTeamLogo: String
)
