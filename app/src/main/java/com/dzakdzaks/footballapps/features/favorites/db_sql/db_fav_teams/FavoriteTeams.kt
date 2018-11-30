package com.dzakdzaks.footballapps.features.favorites.db_sql.db_fav_teams

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTeams(val id: Long?,
                         val teamId: String?
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
    }
}