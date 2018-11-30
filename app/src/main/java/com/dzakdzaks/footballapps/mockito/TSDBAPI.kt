package com.dzakdzaks.footballapps.mockito

import com.dzakdzaks.footballapps.BuildConfig


object TSDBAPI {
    fun getLastMatch(league: String?): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=" +
                league
    }
}