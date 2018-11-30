package com.dzakdzaks.footballapps.features.favorites.fav_teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.adapter.TeamsAdapter
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.features.favorites.db_sql.database
import com.dzakdzaks.footballapps.features.favorites.db_sql.db_fav_teams.FavoriteTeams
import com.dzakdzaks.footballapps.features.teams.team_detail.TeamsDetailActivity
import com.dzakdzaks.footballapps.model.Teams
import com.dzakdzaks.footballapps.utils.invisible
import com.dzakdzaks.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite_teams.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class FavoriteTeamsFragment : Fragment(), FavoriteTeamsView {
    override fun showLoading() {
        progressBarFavoriteTeams.visible()
        rvFavoriteTeams.invisible()
    }

    override fun hideLoading() {
        progressBarFavoriteTeams.invisible()
        rvFavoriteTeams.visible()
    }

    override fun showData(data: List<Teams>) {
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private var event: MutableList<Teams> = mutableListOf()
    private lateinit var adapter: TeamsAdapter
    private lateinit var presenter: FavoriteTeamsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = TeamsAdapter(ctx, event) {
            ctx.startActivity<TeamsDetailActivity>("team" to it)
        }

        rvFavoriteTeams.layoutManager = LinearLayoutManager(this.context)
        rvFavoriteTeams.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = FavoriteTeamsPresenter(this, request, gson)

        showFav()

        refreshFavTeams.setOnRefreshListener {
            event.clear()
            adapter.notifyDataSetChanged()
            showFav()
        }
    }

    private fun showFav() {
        context?.database?.use {
            refreshFavTeams.isRefreshing = false
            val result = select(FavoriteTeams.TABLE_FAVORITE_TEAM)
            val fav = result.parseList(classParser<FavoriteTeams>())
            for (favorite in fav) {
                presenter.getFavTeam(favorite.teamId)
            }
        }
    }


}
