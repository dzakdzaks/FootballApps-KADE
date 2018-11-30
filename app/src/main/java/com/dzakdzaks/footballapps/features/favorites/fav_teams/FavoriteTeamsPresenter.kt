package com.dzakdzaks.footballapps.features.favorites.fav_teams

import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.api.TheSportsDBApi
import com.dzakdzaks.footballapps.model.TeamsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class FavoriteTeamsPresenter(private val view: FavoriteTeamsView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson) {

    fun getFavTeam(teamId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getLookUpTeam(teamId)),
                    TeamsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showData(data.teams)
            }
        }
    }
}