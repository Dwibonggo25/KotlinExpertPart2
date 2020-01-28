package com.example.dicodingexpert2.ui.detailmatch

import com.example.dicodingexpert2.RxImmediateSchedulerRule
import com.example.dicodingexpert2.api.Api
import com.example.dicodingexpert2.api.ApiService
import com.example.dicodingexpert2.model.DetailMatchEvent
import com.example.dicodingexpert2.model.DetailMatchResponse
import com.example.dicodingexpert2.utils.FakeData
import com.example.dicodingexpert2.utils.mock
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Observer
import org.junit.rules.TestRule
import org.mockito.Mockito.verify


@RunWith (MockitoJUnitRunner::class)
class DetailMatchViewModelTest {

    private val detailMatch = FakeData.fakeDataDetailMatch()[0]

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var viewModel: DetailMatchViewModel

    @Before
    fun setUp () {
        MockitoAnnotations.initMocks(this)

        viewModel = DetailMatchViewModel(apiService)
    }


    @Test
    fun fetchDetailMatch (){
        val data = DetailMatchResponse (listOf(
                DetailMatchEvent("22-10-2022","22-10-2022","474747","47474747","","","",
            "","","","","","","",
            "","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","","","","","","",
            "","","")))

        Mockito.`when`(apiService.fetchDetailMatch(detailMatch.idEvent)).thenReturn(Observable.just(data))
        viewModel.fetchDetailMatch(detailMatch.idEvent)
        with(viewModel){
            verify(apiService).fetchDetailMatch(detailMatch.idEvent)
        }
    }

}