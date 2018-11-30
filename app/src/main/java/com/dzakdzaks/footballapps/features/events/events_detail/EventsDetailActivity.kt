package com.dzakdzaks.footballapps.features.events.events_detail

import android.content.res.Configuration
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.R.id.add_to_favorite
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.features.favorites.db_sql.database
import com.dzakdzaks.footballapps.features.favorites.db_sql.db_fav_events.Favorite
import com.dzakdzaks.footballapps.model.Events
import com.dzakdzaks.footballapps.model.Teams
import com.dzakdzaks.footballapps.utils.dateFormater
import com.dzakdzaks.footballapps.utils.invisible
import com.dzakdzaks.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_events_detail.*
import kotlinx.android.synthetic.main.content_events_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class EventsDetailActivity : AppCompatActivity(), EventsDetailView {


    private lateinit var eventsDetailPresenter: EventsDetailPresenter

    private lateinit var match: Events

    var onrotaion = true

    val request = ApiRepository()
    val gson = Gson()

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val data = intent
        id = data.getStringExtra("id")

        favoriteState()


        eventsDetailPresenter = EventsDetailPresenter(this, request, gson)

        eventsDetailPresenter.getEventDetail(id)

        if (onrotaion) {
            eventsDetailPresenter.getEventDetail(id)
        }

        toolbar_layout.setExpandedTitleColor(Color.TRANSPARENT)
        toolbar_layout.setCollapsedTitleTextColor(Color.WHITE)

    }

    fun setPlayerDetail(textView: TextView, players: String) {
        val items = players.split((";").toRegex()).dropLastWhile { it.isEmpty() }.map { it.trim() }.toTypedArray()
        val itemList = items.toMutableList()
        textView.text = itemList.joinToString("\n")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_events_detail, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.support.design.R.id.home -> {
                finish()
                true
            }

            add_to_favorite -> {
                if (isFavorite) removeFav() else
                    addToFav()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFav() {
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to match.eventId
                )
            }
            snackbar(tv_date_detail, "Added to favorite!").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(tv_date_detail, e.localizedMessage).show()
        }
    }

    private fun removeFav() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {idEvent})", "idEvent" to id)
            }
            snackbar(tv_date_detail, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(tv_date_detail, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {idEvent})",
                            "idEvent" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        onrotaion = false
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun showTeamBadge(data: List<Teams>) {
        Glide.with(applicationContext).load(data.get(0).teamBadge).into(
                if (data.get(0).teamName.equals(match.homeTeamName)) {
                    img_home_badge
                } else {
                    img_away_badge
                })
    }

    override fun showDetailEvent(data: List<Events>) {
        match = data[0]
        toolbar_layout.title = match.matchName

        tv_home_name.text = match.homeTeamName
        tv_away_name.text = match.awayTeamName
        Log.d("assss", match.homeTeamName!!.replace(" ", "%20").trim())
        eventsDetailPresenter.getTeamBadge(match.homeTeamName)
        eventsDetailPresenter.getTeamBadge(match.awayTeamName)

        val homeScore = match.homeScore.toString()
        val awayScore = match.awayScore.toString()
        val homeGoalDetail = match.homeGoalDetails.toString()
        val awayGoalDetail = match.awayGoalDetails.toString()
        val homeGK = match.homeLineupGoalkeeper.toString()
        val awayGK = match.awayLineupGoalkeeper.toString()
        val homeDef = match.homeLineupDefense.toString()
        val awayDef = match.awayLineupDefense.toString()
        val homeMid = match.homeLineupMidfield.toString()
        val awayMid = match.awayLineupMidfield.toString()
        val homeFor = match.homeLineupForward.toString()
        val awayFor = match.awayLineupForward.toString()
        val homeSub = match.homeLineupSubstitutes.toString()
        val awaySub = match.awayLineupSubstitutes.toString()
        val homeShots = match.homeShoots.toString() + " Shots"
        val awayShots = match.awayShoots.toString() + " Shots"
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = dateFormat.parse(match.dateEvent.toString())
        val formatedDate = dateFormater(date)
        val timeEvent = match.timeEvent
        val leagueName = match.leagueName


        if (timeEvent != null) {
            if (timeEvent.equals(SimpleDateFormat("hh:mm:ss'+'ss:ss", Locale.getDefault()))) {
                val timeGMTFormat = SimpleDateFormat("hh:mm:ss'+'ss:ss", Locale.getDefault())
                val times = timeGMTFormat.parse(timeEvent)
                val localTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(times)
                val dateAndTime = formatedDate + "\n" + localTime + "\n" + leagueName
                tv_date_detail.text = dateAndTime

            } else if (timeEvent.equals(SimpleDateFormat("hh:mm:ss", Locale.getDefault()))) {
                val timeGMTFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
                val times = timeGMTFormat.parse(timeEvent)
                val localTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(times)
                val dateAndTime = formatedDate + "\n" + localTime + "\n" + leagueName
                tv_date_detail.text = dateAndTime
            } else {
                val timeGMTFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
                val times = timeGMTFormat.parse(timeEvent)
                val localTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(times)
                val dateAndTime = formatedDate + "\n" + localTime + "\n" + leagueName
                tv_date_detail.text = dateAndTime
            }
        }

        if (homeScore.equals("null") || awayScore.equals("null")) {
            tv_home_score_detail.invisible()
            tv_away_score_detail.invisible()
            tv_home_scorer.invisible()
            tv_away_scorer.invisible()
            tv_gk_home.invisible()
            tv_gk_away.invisible()
            tv_def_home.invisible()
            tv_def_away.invisible()
            tv_mid_home.invisible()
            tv_mid_away.invisible()
            tv_for_home.invisible()
            tv_for_away.invisible()
            tv_sub_home.invisible()
            tv_sub_away.invisible()
            tv_home_shots.invisible()
            tv_away_shots.invisible()
            tv_coming_soon.visible()
            tv_coming_soon.text = getString(R.string.this_match_wil_be_held_on) + formatedDate
            toast("This match wil be held on \n" + formatedDate)
        } else {
            tv_home_score_detail.text = homeScore
            tv_away_score_detail.text = awayScore
            setPlayerDetail(tv_home_scorer, homeGoalDetail)
            setPlayerDetail(tv_away_scorer, awayGoalDetail)

            setPlayerDetail(tv_gk_home, homeGK)
            setPlayerDetail(tv_gk_away, awayGK)

            setPlayerDetail(tv_def_home, homeDef)
            setPlayerDetail(tv_def_away, awayDef)

            setPlayerDetail(tv_mid_home, homeMid)
            setPlayerDetail(tv_mid_away, awayMid)

            setPlayerDetail(tv_for_home, homeFor)
            setPlayerDetail(tv_for_away, awayFor)

            setPlayerDetail(tv_sub_home, homeSub)
            setPlayerDetail(tv_sub_away, awaySub)

            setPlayerDetail(tv_home_shots, homeShots)
            setPlayerDetail(tv_away_shots, awayShots)
        }


    }
}
