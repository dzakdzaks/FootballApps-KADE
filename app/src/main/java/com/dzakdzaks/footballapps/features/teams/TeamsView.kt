package com.dzakdzaks.footballapps.features.teams

import com.dzakdzaks.footballapps.model.Teams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Teams>)
}