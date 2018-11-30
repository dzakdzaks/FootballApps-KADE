package com.dzakdzaks.footballapps.features.events

import com.dzakdzaks.footballapps.model.Events

interface EventsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Events>)
}