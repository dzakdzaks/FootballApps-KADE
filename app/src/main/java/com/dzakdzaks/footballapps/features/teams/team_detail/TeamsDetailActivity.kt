package com.dzakdzaks.footballapps.features.teams.team_detail

import android.content.res.Configuration
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.adapter.ViewPagerAdapter
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.features.favorites.db_sql.database
import com.dzakdzaks.footballapps.features.favorites.db_sql.db_fav_teams.FavoriteTeams
import com.dzakdzaks.footballapps.features.players.PlayersFragment
import com.dzakdzaks.footballapps.model.Teams
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamsDetailActivity : AppCompatActivity(), TeamsDetailView {
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showDetailEvent(data: List<Teams>) {
        teams = data[0]

        toolbar_layout_team.title = teams.teamName

        tv_team_name.text = teams.teamName
        tv_date_team_detail.text = teams.intFormedYear
        tv_stadium_team_detail.text = teams.strStadium

        Glide.with(applicationContext).load(data[0].teamBadge).into(img_team_badge)

    }

    private lateinit var teamsDetailPresenter: TeamsDetailPresenter

    private lateinit var teams: Teams

    var onrotaion = true

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        setSupportActionBar(toolbar_team)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_team.setNavigationOnClickListener { onBackPressed() }

        teams = if (savedInstanceState != null) {
            savedInstanceState.getParcelable("team")
        } else {
            intent.getParcelableExtra("team")
        }

        val bundle = Bundle()
        bundle.putParcelable("teams", teams)

        id = teams.idTeam.toString()
        Log.d("id", id)
        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        teamsDetailPresenter = TeamsDetailPresenter(this, request, gson)

        teamsDetailPresenter.getTeamDetail(id)

        if (onrotaion) {
            teamsDetailPresenter.getTeamDetail(id)
        }

        toolbar_layout_team.setExpandedTitleColor(Color.TRANSPARENT)
        toolbar_layout_team.setCollapsedTitleTextColor(Color.WHITE)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        val overviewsFragment = OverviewsFragment()
        val playersFragment = PlayersFragment()
        overviewsFragment.arguments = bundle
        playersFragment.arguments = bundle
        adapter.addFragment(overviewsFragment, "Overviews")
        adapter.addFragment(playersFragment, "Players")
        viewpagerTeam.adapter = adapter
        tabTeam.setupWithViewPager(viewpagerTeam)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_team_detail, menu)
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

            R.id.add_to_favorite_team -> {
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
                insert(FavoriteTeams.TABLE_FAVORITE_TEAM,
                        FavoriteTeams.TEAM_ID to teams.idTeam
                )
            }
            snackbar(tv_team_name, "Added to favorite!").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(tv_team_name, e.localizedMessage).show()
        }
    }

    private fun removeFav() {
        try {
            database.use {
                delete(FavoriteTeams.TABLE_FAVORITE_TEAM, "(TEAM_ID = {idTeam})", "idTeam" to id)
            }
            snackbar(tv_team_name, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(tv_team_name, e.localizedMessage).show()
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
            val result = select(FavoriteTeams.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {idTeam})",
                            "idTeam" to id)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        onrotaion = false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("team", teams)

    }


}
