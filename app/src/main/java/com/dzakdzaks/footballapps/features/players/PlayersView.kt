package com.dzakdzaks.footballapps.features.players

import com.dzakdzaks.footballapps.model.Players


interface PlayersView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Players>)
}