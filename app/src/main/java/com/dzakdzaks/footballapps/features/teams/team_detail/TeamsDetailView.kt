package com.dzakdzaks.footballapps.features.teams.team_detail

import com.dzakdzaks.footballapps.model.Teams

interface TeamsDetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailEvent(data: List<Teams>)
}