package com.dzakdzaks.footballapps.features.home

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.dzakdzaks.footballapps.R.id.*
import com.dzakdzaks.footballapps.espresso.EspressoIdlingResource
import org.junit.After
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityTest = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun test(){
        delayTime()
        onView(withId(rvNextEvents))
                .check(matches(isDisplayed()))
        onView(withId(rvNextEvents)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(rvNextEvents)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        delayTime()

        onView(withId(img_away_badge)).check(matches(isDisplayed()))
        onView(withId(img_home_badge)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(click())

        delayTime()
        pressBackCustom()

        delayTime()

        onView(withId(viewpagerEvents)).perform(swipeLeft())

        delayTime()

        onView(withId(rvLastEvents))
                .check(matches(isDisplayed()))
        onView(withId(rvLastEvents)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(rvLastEvents)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        delayTime()

        onView(withId(img_away_badge)).check(matches(isDisplayed()))
        onView(withId(img_home_badge)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite)).perform(click())

        delayTime()
        pressBackCustom()
        delayTime()

        onView(withId(navTeams)).perform(click())

        delayTime()

        onView(withId(rvTeams)).check(matches(isDisplayed()))
        onView(withId(rvTeams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rvTeams)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        delayTime()

        onView(withId(img_team_badge)).check(matches(isDisplayed()))

        delayTime()

        onView(withId(add_to_favorite_team)).perform(click())

        delayTime()
        pressBackCustom()

        onView(withId(navFavorites)).perform(click())

        delayTime()

        onView(withId(rvFavoriteEvents)).check(matches(isDisplayed()))
        onView(withId(rvFavoriteEvents)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rvFavoriteEvents)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(img_away_badge)).check(matches(isDisplayed()))
        onView(withId(img_home_badge)).check(matches(isDisplayed()))

        delayTime()

        onView(withId(add_to_favorite)).perform(click())

        delayTime()

        pressBackCustom()

        delayTime()

        onView(withId(refreshFavEvents)).perform(swipeDown())

        delayTime()

        onView(withId(viewpagerFavorites)).perform(swipeLeft())

        delayTime()

        onView(withId(rvFavoriteTeams)).check(matches(isDisplayed()))
        onView(withId(rvFavoriteTeams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rvFavoriteTeams)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(img_team_badge)).check(matches(isDisplayed()))

        delayTime()

        onView(withId(add_to_favorite_team)).perform(click())

        delayTime()

        pressBackCustom()

        delayTime()

        onView(withId(refreshFavTeams)).perform(swipeDown())

        delayTime()

    }

    private fun delayTime(){
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun pressBackCustom() {
        onView(isRoot()).perform(pressBack())
    }
}