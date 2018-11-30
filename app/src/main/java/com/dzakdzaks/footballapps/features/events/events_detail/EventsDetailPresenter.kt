package com.dzakdzaks.footballapps.features.events.events_detail

import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.api.TheSportsDBApi
import com.dzakdzaks.footballapps.model.EventsResponse
import com.dzakdzaks.footballapps.model.TeamsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EventsDetailPresenter(private val viewEvents: EventsDetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun getEventDetail(teamId: String?) {
        viewEvents.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getLookUpEvent(teamId)),
                    EventsResponse::class.java
            )

            uiThread {
                viewEvents.hideLoading()
                viewEvents.showDetailEvent(data.events)
            }
        }
    }

    fun getTeamBadge(teams: String?) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getSearchTeam(teams!!.replace(" ", "%20").trim())),
                    TeamsResponse::class.java)


            uiThread {
                viewEvents.showTeamBadge(data.teams)
            }
        }
    }
}