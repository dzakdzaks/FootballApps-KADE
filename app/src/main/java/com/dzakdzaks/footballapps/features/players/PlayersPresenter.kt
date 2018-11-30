package com.dzakdzaks.footballapps.features.players

import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.api.TheSportsDBApi
import com.dzakdzaks.footballapps.model.PlayersResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayersPresenter(private val view: PlayersView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson) {

    fun getAllPlayers(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(TheSportsDBApi.getLookUpAllPlayers(id)),
                    PlayersResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showPlayerList(data.player)
            }
        }
    }
}