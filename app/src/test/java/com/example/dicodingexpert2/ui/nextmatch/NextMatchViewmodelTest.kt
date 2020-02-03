package com.example.dicodingexpert2.ui.nextmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingexpert2.RxImmediateSchedulerRule
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.db.DicodingDb
import com.example.dicodingexpert2.model.Team
import com.example.dicodingexpert2.model.TeamLogoResponse
import com.example.dicodingexpert2.utils.FakeData
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NextMatchViewmodelTest {

    var fakeData = FakeData.fakeDataNextMatch()[0]

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewmodel: NextMatchViewmodel

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var api: ApiService

    @Mock
    private lateinit var db: DicodingDb

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewmodel = NextMatchViewmodel(db, api)
    }

    var data =
        TeamLogoResponse(listOf(Team("22-10-2022", "22-10-2022", "474747", "47474747", "", "")))

    @Test
    fun fetchLogoHomeTeam() {
        Mockito.`when`(api.fetchLogoTeam(fakeData.idHomeTeam)).thenReturn(Observable.just(data))
        viewmodel.fetchLogoHomeTeam(fakeData)
    }

    @Test
    fun fetchLogoAwayTeam() {
        Mockito.`when`(api.fetchLogoTeam(fakeData.idAwayTeam)).thenReturn(Observable.just(data))
        viewmodel.fetchLogoAwayTeam(fakeData)
    }
}