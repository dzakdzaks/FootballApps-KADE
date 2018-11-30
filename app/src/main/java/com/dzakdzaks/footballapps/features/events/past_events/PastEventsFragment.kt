package com.dzakdzaks.footballapps.features.events.past_events


import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.adapter.EventsAdapter
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.features.events.EventsPresenter
import com.dzakdzaks.footballapps.features.events.EventsView
import com.dzakdzaks.footballapps.features.events.events_detail.EventsDetailActivity
import com.dzakdzaks.footballapps.model.Events
import com.dzakdzaks.footballapps.utils.invisible
import com.dzakdzaks.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_past_events.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textColor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PastEventsFragment : Fragment(), EventsView {

    private var events: MutableList<Events> = mutableListOf()
    private lateinit var presenter: EventsPresenter
    private lateinit var adapter: EventsAdapter
    val request = ApiRepository()
    val gson = Gson()
    lateinit var leagueName: String
    var onrotation = true
    var rootView: View? = null

    override fun showLoading() {
//        activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBarLastEvents.visible()
        rvLastEvents.invisible()
    }

    override fun hideLoading() {
        progressBarLastEvents.invisible()
        rvLastEvents.visible()
    }

    override fun showTeamList(data: List<Events>) {
//        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        refreshPastEvents.isRefreshing = false
        events.clear()
        events.addAll(data)
        rvLastEvents.scrollToPosition(0)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_events, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = EventsAdapter(ctx, events) {
            ctx.startActivity<EventsDetailActivity>("id" to it.eventId)
        }

        rvLastEvents.layoutManager = LinearLayoutManager(this.context)
        rvLastEvents.adapter = adapter

        presenter = EventsPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerLastEvents.adapter = spinnerAdapter

        spinnerLastEvents.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                leagueName = spinnerLastEvents.selectedItem.toString()

                (p0.getChildAt(0) as TextView).textSize = 20f
                (p0.getChildAt(0) as TextView).textColor = Color.WHITE
                (p0.getChildAt(0) as TextView).textAlignment = View.TEXT_ALIGNMENT_CENTER

                if (onrotation) {
                    when (leagueName) {
                        "English Premier League" -> presenter.getLastMatch("4328")
                        "German Bundesliga" -> presenter.getLastMatch("4331")
                        "Italian Serie A" -> presenter.getLastMatch("4332")
                        "French Ligue 1" -> presenter.getLastMatch("4334")
                        "Spanish La Liga" -> presenter.getLastMatch("4335")
                        "Netherlands Eredivisie" -> presenter.getLastMatch("4337")
                    }
                }

                when (leagueName) {
                    "English Premier League" -> presenter.getLastMatch("4328")
                    "German Bundesliga" -> presenter.getLastMatch("4331")
                    "Italian Serie A" -> presenter.getLastMatch("4332")
                    "French Ligue 1" -> presenter.getLastMatch("4334")
                    "Spanish La Liga" -> presenter.getLastMatch("4335")
                    "Netherlands Eredivisie" -> presenter.getLastMatch("4337")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        refreshPastEvents.setOnRefreshListener {
            events.clear()
            adapter.notifyDataSetChanged()
            when (leagueName) {
                "English Premier League" -> presenter.getLastMatch("4328")
                "German Bundesliga" -> presenter.getLastMatch("4331")
                "Italian Serie A" -> presenter.getLastMatch("4332")
                "French Ligue 1" -> presenter.getLastMatch("4334")
                "Spanish La Liga" -> presenter.getLastMatch("4335")
                "Netherlands Eredivisie" -> presenter.getLastMatch("4337")
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        onrotation = false
    }


}
