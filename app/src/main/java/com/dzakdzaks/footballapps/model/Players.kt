package com.dzakdzaks.footballapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Players(
        @SerializedName("strPlayer")
        var strPlayer: String?,
        @SerializedName("dateBorn")
        var dateBorn: String?,
        @SerializedName("strNationality")
        var strNationality: String?,
        @SerializedName("strSport")
        var strSport: String?,
        @SerializedName("strInstagram")
        var strInstagram: String?,
        @SerializedName("idTeam")
        var idTeam: String?,
        @SerializedName("strDescriptionEN")
        var strDescriptionEN: String?,
        @SerializedName("strBirthLocation")
        var strBirthLocation: String?,
        @SerializedName("strWebsite")
        var strWebsite: String?,
        @SerializedName("strPosition")
        var strPosition: String?,
        @SerializedName("strYoutube")
        var strYoutube: String?,
        @SerializedName("strCutout")
        var strCutout: String?,
        @SerializedName("idPlayerManager")
        var idPlayerManager: String?,
        @SerializedName("strLocked")
        var strLocked: String?,
        @SerializedName("intLoved")
        var intLoved: String?,
        @SerializedName("idSoccerXML")
        var idSoccerXML: String?,
        @SerializedName("strTeam")
        var strTeam: String?,
        @SerializedName("intSoccerXMLTeamID")
        var intSoccerXMLTeamID: String?,
        @SerializedName("strSigning")
        var strSigning: String?,
        @SerializedName("strTwitter")
        var strTwitter: String?,
        @SerializedName("strHeight")
        var strHeight: String?,
        @SerializedName("strWeight")
        var strWeight: String?,
        @SerializedName("strGender")
        var strGender: String?,
        @SerializedName("strFacebook")
        var strFacebook: String?,
        @SerializedName("strCollege")
        var strCollege: String?,
        @SerializedName("idPlayer")
        var idPlayer: String?,
        @SerializedName("strThumb")
        var strThumb: String?,
        @SerializedName("strWage")
        var strWage: String?,
        @SerializedName("dateSigned")
        var dateSigned: String?
) : Parcelable
