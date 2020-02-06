package com.example.dicodingexpert2.ui.home

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.dicodingexpert2.R
import com.example.dicodingexpert2.ui.MainActivity
import com.example.dicodingexpert2.utils.EspressoResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeLeagueFragmentTest {

    private val testingSearch = "Liverpool"

    private val empty = "Kosong"

    @Rule
    @JvmField var activityResult = ActivityTestRule (MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoResource.idlingResource)
    }

    @Test
    fun searchLeague() {

        Thread.sleep(3000)
        onView(withId(R.id.searchMenu)).check(matches(isDisplayed()))
        onView(withId(R.id.searchMenu)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText(testingSearch), closeSoftKeyboard())
    }


    @Test
    fun searchEmpty () {
        Thread.sleep(3000)
        onView(withId(R.id.searchMenu)).check(matches(isDisplayed()))
        onView(withId(R.id.searchMenu)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText(empty))
            .perform(pressImeActionButton())
    }

    @Test
    fun buttonDelete() {
        Thread.sleep(3000)
        onView(withId(R.id.searchMenu)).check(matches(isDisplayed()))
        onView(withId(R.id.searchMenu)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(clearText())
            .perform(pressImeActionButton())
    }

}