package com.dzakdzaks.footballapps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.model.Teams
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_teams.*

class TeamsAdapter(private val context: Context, private val teams: List<Teams>, private val listener: (Teams) -> Unit) : RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            TeamsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_teams, p0, false))

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(p0: TeamsAdapter.TeamsViewHolder, p1: Int) {
        p0.bindItem(teams[p1], listener)

    }

    class TeamsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(teams: Teams, listener: (Teams) -> Unit) {
            val teamBadge = teams.teamBadge
            val teamName = teams.teamName
            val teamLeague = teams.strLeague

            Glide.with(containerView).load(teamBadge).into(ivTeam)
            tvTeamName.text = teamName
            tvTeamLeague.text = teamLeague

            containerView.setOnClickListener { listener(teams) }
        }
    }
}