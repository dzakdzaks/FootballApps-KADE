package com.dzakdzaks.footballapps.features.favorites.db_sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.dzakdzaks.footballapps.features.favorites.db_sql.db_fav_events.Favorite
import com.dzakdzaks.footballapps.features.favorites.db_sql.db_fav_teams.FavoriteTeams
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT + UNIQUE
        )
        db.createTable(FavoriteTeams.TABLE_FAVORITE_TEAM, true,
                FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeams.TEAM_ID to TEXT + UNIQUE
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeams.TABLE_FAVORITE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)