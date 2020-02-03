package com.example.dicodingexpert2.ui.previousmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingexpert2.RxImmediateSchedulerRule
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.db.DicodingDb
import com.example.dicodingexpert2.model.PreviousMatch
import com.example.dicodingexpert2.model.PreviousMatchResponse
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

@RunWith (MockitoJUnitRunner::class)
class PreviousMatchViewmodelTest {

    val fakeData = FakeData.fakeDataPreviousMatch()[0]

    val dataResponse = PreviousMatchResponse (listOf(PreviousMatch("22-10-2022","22-10-2022","474747","47474747","","","",
        "","","","","","","",
        "","","","","","",
        "","","","","","","",
        "","","","","","","",
        "","","","","","","",
        "","","","","","","",
        "","","","","","","",
        "","","")))

    var data =  TeamLogoResponse(listOf( Team("22-10-2022","22-10-2022","474747","47474747","","")))

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var viewModel : PreviousMatchViewmodel

    @Mock
    private lateinit var repository : DicodingDb

    @Mock
    private lateinit var api: ApiService

    @Before
    fun setUp () {
        MockitoAnnotations.initMocks(this)
        viewModel = PreviousMatchViewmodel(repository, api)
    }

    @Test
    fun fetchLogoHomeTeam() {
        Mockito.`when`(api.fetchLogoTeam(fakeData.idHomeTeam)).thenReturn(Observable.just(data))
        viewModel.fetchLogoHomeTeam(fakeData)
    }

    @Test
    fun fetchLogoAwayTeam() {
        Mockito.`when`(api.fetchLogoTeam(fakeData.idAwayTeam)).thenReturn(Observable.just(data))
        viewModel.fetchLogoAwayTeam(fakeData)
    }

    @Test
    fun saveInfavoriteDatabase() {
        
    }

}