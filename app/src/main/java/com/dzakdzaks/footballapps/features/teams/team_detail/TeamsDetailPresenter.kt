package com.dzakdzaks.footballapps.features.teams.team_detail

import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.api.TheSportsDBApi
import com.dzakdzaks.footballapps.model.TeamsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamsDetailPresenter(private val viewTeams: TeamsDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {

    fun getTeamDetail(teamId: String?) {
        viewTeams.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getLookUpTeam(teamId)),
                    TeamsResponse::class.java
            )

            uiThread {
                viewTeams.hideLoading()
                viewTeams.showDetailEvent(data.teams)
            }
        }
    }
}