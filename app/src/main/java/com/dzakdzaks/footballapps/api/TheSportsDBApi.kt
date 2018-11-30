package com.dzakdzaks.footballapps.api

import android.util.Log
import com.dzakdzaks.footballapps.BuildConfig

object TheSportsDBApi {

    fun getPastEvent(league: String?): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventspastleague.php?id=" +
                league
    }

    fun getNextEvent(league: String?): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/eventsnextleague.php?id=" +
                league
    }

    fun getSearchEvent(event: String?): String {
        Log.d("hilihhhh", BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/searchevents.php?e=" +
                event)
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/searchevents.php?e=" +
                event
    }

    fun getLookUpTeam(team: String?): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupteam.php?id=" +
                team
    }

    fun getSearchTeam(team: String?): String {
        Log.d("TEAM BADGE", BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/searchteams.php?t=" +
                team)
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/searchteams.php?t=" +
                team
    }

    fun getLookUpAllTeam(team: String?): String {
        Log.d("all team", BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookup_all_teams.php?id=" +
                team)
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookup_all_teams.php?id=" +
                team
    }

    fun getLookUpEvent(event: String?): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupevent.php?id=" +
                event
    }

    fun getLookUpAllPlayers(team: String?): String {
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookup_all_players.php?id=" +
                team
    }

    fun getLookUpPlayer(player: String?): String {
        Log.d("lookup player", BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupplayer.php?id=" +
                player)
        return BuildConfig.BASE_URL +
                "api/v1/json/${BuildConfig.TSDB_API_KEY}" +
                "/lookupplayer.php?id=" +
                player
    }
}