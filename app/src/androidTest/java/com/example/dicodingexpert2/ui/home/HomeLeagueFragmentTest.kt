package com.example.dicodingexpert2.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith (AndroidJUnit4ClassRunner::class)
class HomeLeagueFragmentTest {

    @Rule
    @JvmField val activityResut = ActivityTestRule(MainActivity::class.java)

    @Test
    fun functionSearch () {
        onView(withId(R.id.tvTitle)).perform(typeText(""), closeSoftKeyboard())
    }
}