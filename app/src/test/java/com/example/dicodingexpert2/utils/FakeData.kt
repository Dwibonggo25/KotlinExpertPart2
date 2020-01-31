package com.example.dicodingexpert2.utils

import com.example.dicodingexpert2.model.*

object FakeData {

    fun fakeDataDummyDetailLeague(): List<LeagueDetail> {
        return listOf(
            LeagueDetail("22-10-2022","22-10-2022","474747","47474747","","","",
                "","","","","","","",
                "","","","","","",
                "","","","","","","",
                "","","","","","","",
                "","","","","","","",
                "",""))
    }

    fun fakeDataNextMatch(): List<EventFootball> {
        return listOf(
            EventFootball("22-10-2022","22-10-2022","474747","47474747","","","",
                "","","","","","","",
                "","","","","","",
                "","","","","","","",
                "","","","","","","",
                "","","","","","","",
                "","","","","","","",
                "","","","","","","",
                "","",""))
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