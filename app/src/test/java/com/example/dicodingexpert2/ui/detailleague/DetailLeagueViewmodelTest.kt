package com.example.dicodingexpert2.ui.detailleague

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingexpert2.RxImmediateSchedulerRule
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.model.DetailLeagueResponse
import com.example.dicodingexpert2.model.LeagueDetail
import com.example.dicodingexpert2.utils.FakeData
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailLeagueViewmodelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var apiService : ApiService

    @Mock
    lateinit var viewmodel: DetailLeagueViewmodel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewmodel = DetailLeagueViewmodel(apiService)
    }

    private val detailLeague = FakeData.fakeDataDummyDetailLeague()[0]

    private val dataTest = DetailLeagueResponse(listOf(LeagueDetail("22-10-2022","22-10-2022","474747","47474747","","","",
        "","","","","","","",
        "","","","","","",
        "","","","","","","",
        "","","","","","","",
        "","","","","","","",
        "","")))

    @Test
    fun getDetailLeague() {
        Mockito.`when`(apiService.getDetailLeague(detailLeague.idLeague)).thenReturn(Observable.just(dataTest))
        viewmodel.getDetailLeague(detailLeague.idLeague)
        with(viewmodel){
            verify(apiService).getDetailLeague(detailLeague.idLeague)
        }
    }

}