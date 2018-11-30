package com.dzakdzaks.footballapps.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.model.Events
import com.dzakdzaks.footballapps.utils.dateFormater
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_events.*
import java.text.SimpleDateFormat
import java.util.*

class EventsAdapter(private val context: Context, private val events: List<Events>, private val listener: (Events) -> Unit) : RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            EventsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_events, parent, false))


    override fun getItemCount(): Int {
        return events.size

    }

    override fun onBindViewHolder(p0: EventsViewHolder, p1: Int) {
        p0.bindItem(events[p1], listener)
    }

    class EventsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        @SuppressLint("SimpleDateFormat")
        fun bindItem(events: Events, listener: (Events) -> Unit) {

            val dateEvent = events.dateEvent
            val timeEvent = events.timeEvent
            val homeTeamName = events.homeTeamName
            val awayTeamName = events.awayTeamName
            val leagueName = events.leagueName
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = dateFormat.parse(dateEvent)
            val formatedDate = dateFormater(date)

            if (timeEvent != null) {
                if (timeEvent.equals(SimpleDateFormat("hh:mm:ss'+'ss:ss", Locale.getDefault()))) {
                    val timeGMTFormat = SimpleDateFormat("hh:mm:ss'+'ss:ss", Locale.getDefault())
                    val times = timeGMTFormat.parse(timeEvent)
                    val localTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(times)
                    val dateAndTime = formatedDate + "\n" + localTime
                    tv_date.text = dateAndTime

                } else if (timeEvent.equals(SimpleDateFormat("hh:mm:ss", Locale.getDefault()))) {
                    val timeGMTFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
                    val times = timeGMTFormat.parse(timeEvent)
                    val localTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(times)
                    val dateAndTime = formatedDate + "\n" + localTime
                    tv_date.text = dateAndTime
                } else {
                    val timeGMTFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
                    val times = timeGMTFormat.parse(timeEvent)
                    val localTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(times)
                    val dateAndTime = formatedDate + "\n" + localTime
                    tv_date.text = dateAndTime
                }
            }


            tv_home_team.text = homeTeamName
            tv_away_team.text = awayTeamName
            if (events.homeScore == null && events.awayScore == null) {
                tv_score_home.text = "-"
                tv_score_away.text = "-"
            } else {
                tv_score_home.text = events.homeScore.toString()
                tv_score_away.text = events.awayScore.toString()
            }

            tvLeagueName.text = leagueName

            containerView.setOnClickListener { listener(events) }
        }
    }
}