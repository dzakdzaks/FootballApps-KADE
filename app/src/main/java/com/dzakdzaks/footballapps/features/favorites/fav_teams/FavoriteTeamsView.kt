package com.dzakdzaks.footballapps.features.favorites.fav_teams

import com.dzakdzaks.footballapps.model.Teams

interface FavoriteTeamsView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<Teams>)
}