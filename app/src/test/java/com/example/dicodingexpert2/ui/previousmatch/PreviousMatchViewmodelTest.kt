package com.example.dicodingexpert2.ui.previousmatch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dicodingexpert2.RxImmediateSchedulerRule
import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock

class PreviousMatchViewmodelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var viewModel : PreviousMatchViewmodel


}