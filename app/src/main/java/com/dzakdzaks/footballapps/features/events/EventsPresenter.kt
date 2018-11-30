package com.dzakdzaks.footballapps.features.events

import android.util.Log
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.api.TheSportsDBApi
import com.dzakdzaks.footballapps.model.EventsResponse
import com.dzakdzaks.footballapps.model.SearchEventsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class EventsPresenter(private val view: EventsView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson) {

    fun getLastMatch(match: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getPastEvent(match)),
                    EventsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.events)
            }
        }
    }

    fun getNextMatch(match: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getNextEvent(match)),
                    EventsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.events)
                Log.d("next match : ", data.events.toString())
            }
        }
    }

    fun getsearchMatch(match: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getSearchEvent(match!!.replace(" ", "%20").trim())),
                    SearchEventsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                if (data.event == null) {
                    view.showTeamList(Collections.emptyList())
                } else {
                    view.showTeamList(data.event)
                }
            }
        }
    }
}