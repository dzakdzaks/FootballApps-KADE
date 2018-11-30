package com.dzakdzaks.footballapps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.model.Players
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_players.*

class PlayersAdapter(private val context: Context, private val players: List<Players>, private val listener: (Players) -> Unit) : RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            PlayersViewHolder(LayoutInflater.from(context).inflate(R.layout.list_players, p0, false))

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(p0: PlayersAdapter.PlayersViewHolder, p1: Int) {
        p0.bindItem(players[p1], listener)

    }

    class PlayersViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(players: Players, listener: (Players) -> Unit) {
            val playerImage = players.strCutout
            val playerName = players.strPlayer
            val playerPosition = players.strPosition

            Glide.with(containerView).load(playerImage).into(ivPlayer)
            tvPlayerName.text = playerName
            tvPlayerPosition.text = playerPosition

            containerView.setOnClickListener { listener(players) }
        }
    }
}