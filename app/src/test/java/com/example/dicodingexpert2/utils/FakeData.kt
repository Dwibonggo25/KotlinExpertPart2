package com.example.dicodingexpert2.utils

import com.example.dicodingexpert2.model.*

object FakeData {

    fun fakeDataDummyDetailLeague(): List<LeagueDetail> {
        return listOf()
    }

    fun fakeDataNextMatch(): List<EventFootball> {
        return listOf()
    }

    fun fakeDataPreviousMatch(): List<PreviousMatch> {
        return listOf()
    }

    fun fakeDataDetailMatch(): List<DetailMatchEvent> {
        return listOf(DetailMatchEvent("22-10-2022","22-10-2022","474747","47474747","","","",
            "","","","","","","",
            "","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","",""))
    }
}