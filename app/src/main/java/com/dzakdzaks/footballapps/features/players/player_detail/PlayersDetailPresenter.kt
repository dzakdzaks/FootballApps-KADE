package com.dzakdzaks.footballapps.features.players.player_detail

import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.api.TheSportsDBApi
import com.dzakdzaks.footballapps.model.PlayersDetailResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayersDetailPresenter(private val viewPlayer: PlayersDetailView,
                             private val apiRepository: ApiRepository,
                             private val gson: Gson) {

    fun getPlayerDetail(playerId: String?) {
        viewPlayer.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportsDBApi.getLookUpPlayer(playerId)),
                    PlayersDetailResponse::class.java
            )

            uiThread {
                viewPlayer.hideLoading()
                viewPlayer.showPlayerDetail(data.players)
            }
        }
    }
}