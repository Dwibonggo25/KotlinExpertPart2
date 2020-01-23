package com.example.dicodingexpert2.model


import com.google.gson.annotations.SerializedName

data class TeamLogoResponse(
    @SerializedName("teams")
    val teams: List<Team>
)

data class Team(
    @SerializedName("strTeamBadge")
    val strTeamBadge: String,
    @SerializedName("strTeamBanner")
    val strTeamBanner: String,
    @SerializedName("strTeamFanart1")
    val strTeamFanart1: String,
    @SerializedName("strTeamFanart2")
    val strTeamFanart2: String,
    @SerializedName("strTeamJersey")
    val strTeamJersey: String,
    @SerializedName("strTeamLogo")
    val strTeamLogo: String
)
