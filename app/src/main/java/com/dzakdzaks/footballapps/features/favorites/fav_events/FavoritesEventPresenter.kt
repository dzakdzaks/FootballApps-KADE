package com.dzakdzaks.footballapps.features.favorites.fav_events

import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.api.TheSportsDBApi
import com.dzakdzaks.footballapps.model.EventsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class FavoritesEventPresenter(private val view: FavoritesEventsView,
                              private val apiRepository: ApiRepository,
                              private val gson: Gson) {

    fun getFavEvent(teamId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getLookUpEvent(teamId)),
                    EventsResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showData(data.events)
            }
        }
    }
}
