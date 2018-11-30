package com.dzakdzaks.footballapps.features.teams

import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.api.TheSportsDBApi
import com.dzakdzaks.footballapps.model.TeamsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson) {

    fun getAllTeam(match: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getLookUpAllTeam(match)),
                    TeamsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }

    fun getSearchTeam(query: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getSearchTeam(query!!.replace(" ", "%20").trim())),
                    TeamsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if (data.teams == null) {
                    view.showTeamList(Collections.emptyList())
                } else {
                    view.showTeamList(data.teams)
                }
            }
        }
    }
}