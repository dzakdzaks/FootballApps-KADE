package com.dzakdzaks.footballapps.features.favorites.db_sql.db_fav_events

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(val id: Long?,
                    val eventId: String?
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
    }
}