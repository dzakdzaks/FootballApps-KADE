package com.dzakdzaks.footballapps.features.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.R.id.*
import com.dzakdzaks.footballapps.features.events.EventsFragment
import com.dzakdzaks.footballapps.features.favorites.FavoritesFragment
import com.dzakdzaks.footballapps.features.teams.TeamsFragment
import com.dzakdzaks.footballapps.utils.gone
import com.dzakdzaks.footballapps.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_nav_view.*

class MainActivity : AppCompatActivity() {

    var events = 0
    var teams = 0
    var favorites = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                navEvents -> {
                    eventsFrag()
                }
                navTeams -> {
                    teamsFrag()
                }

                navFavorites -> {
                    favoritesFrag()
                }
            }
            true
        }
        bottom_navigation.selectedItemId = navEvents
    }

    private fun eventsFrag() {
        if (events == 0) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_events, EventsFragment(), EventsFragment::class.java.simpleName)
                    .commit()
            events = 1
        }
        frame_events.visible()
        frame_teams.gone()
        frame_favorites.gone()
    }

    private fun teamsFrag() {
        if (teams == 0) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_teams, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
            teams = 1
        }
        frame_events.gone()
        frame_teams.visible()
        frame_favorites.gone()
    }

    private fun favoritesFrag() {
        if (favorites == 0) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_favorites, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                    .commit()
            favorites = 1
        }
        frame_events.gone()
        frame_teams.gone()
        frame_favorites.visible()
    }
}
