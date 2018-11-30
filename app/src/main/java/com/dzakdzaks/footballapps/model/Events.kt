package com.dzakdzaks.footballapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
        @SerializedName("idEvent")
        var eventId: String?,
        @SerializedName("idLeague")
        var leagueId: String?,
        @SerializedName("strLeague")
        var leagueName: String?,
        @SerializedName("strHomeTeam")
        var homeTeamName: String?,
        @SerializedName("strAwayTeam")
        var awayTeamName: String?,
        @SerializedName("intHomeScore")
        var homeScore: Int?,
        @SerializedName("intAwayScore")
        var awayScore: Int?,
        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String?,
        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String?,
        @SerializedName("strHomeLineupGoalkeeper")
        var homeLineupGoalkeeper: String?,
        @SerializedName("strHomeLineupDefense")
        var homeLineupDefense: String?,
        @SerializedName("strHomeLineupMidfield")
        var homeLineupMidfield: String?,
        @SerializedName("strHomeLineupForward")
        var homeLineupForward: String?,
        @SerializedName("strHomeLineupSubstitutes")
        var homeLineupSubstitutes: String?,
        @SerializedName("strAwayLineupGoalkeeper")
        var awayLineupGoalkeeper: String?,
        @SerializedName("strAwayLineupDefense")
        var awayLineupDefense: String?,
        @SerializedName("strAwayLineupMidfield")
        var awayLineupMidfield: String?,
        @SerializedName("strAwayLineupForward")
        var awayLineupForward: String?,
        @SerializedName("strAwayLineupSubstitutes")
        var awayLineupSubstitutes: String?,
        @SerializedName("intHomeShots")
        var homeShoots: Int?,
        @SerializedName("intAwayShots")
        var awayShoots: Int?,
        @SerializedName("dateEvent")
        var dateEvent: String?,
        @SerializedName("idHomeTeam")
        var homeIdTeam: String?,
        @SerializedName("idAwayTeam")
        var awayIdTeam: String?,
        @SerializedName("strEvent")
        var matchName: String?,
        @SerializedName("strTime")
        var timeEvent: String?
) : Parcelable