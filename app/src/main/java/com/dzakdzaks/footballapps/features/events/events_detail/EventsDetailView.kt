package com.dzakdzaks.footballapps.features.events.events_detail

import com.dzakdzaks.footballapps.model.Events
import com.dzakdzaks.footballapps.model.Teams

interface EventsDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailEvent(data: List<Events>)
    fun showTeamBadge(data: List<Teams>)
}