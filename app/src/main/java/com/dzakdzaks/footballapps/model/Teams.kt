package com.dzakdzaks.footballapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
        @SerializedName("idLeague")
        var idLeague: String?,
        @SerializedName("idTeam")
        var idTeam: String?,
        @SerializedName("strTeamBadge")
        var teamBadge: String?,
        @SerializedName("strTeam")
        var teamName: String?,
        @SerializedName("intFormedYear")
        var intFormedYear: String?,
        @SerializedName("strStadium")
        var strStadium: String?,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String?,
        @SerializedName("strLeague")
        var strLeague: String?
) : Parcelable