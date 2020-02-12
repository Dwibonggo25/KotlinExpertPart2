package com.example.dicodingexpert2.model


import com.google.gson.annotations.SerializedName

data class ClassmentResponse(
    @SerializedName("table")
    val classment: List<Classment>
)

data class Classment(
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("goalsagainst")
    val goalsagainst: Int,
    @SerializedName("goalsdifference")
    val goalsdifference: Int,
    @SerializedName("goalsfor")
    val goalsfor: Int,
    @SerializedName("loss")
    val loss: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("played")
    val played: Int,
    @SerializedName("teamid")
    val teamid: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("win")
    val win: Int
)