package com.dzakdzaks.footballapps.features.players.player_detail

import com.dzakdzaks.footballapps.model.Players


interface PlayersDetailView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<Players>)
}