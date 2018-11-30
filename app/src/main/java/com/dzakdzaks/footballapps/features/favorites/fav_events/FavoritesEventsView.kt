package com.dzakdzaks.footballapps.features.favorites.fav_events

import com.dzakdzaks.footballapps.model.Events

interface FavoritesEventsView {
    fun showLoading()
    fun hideLoading()
    fun showData(data: List<Events>)
}