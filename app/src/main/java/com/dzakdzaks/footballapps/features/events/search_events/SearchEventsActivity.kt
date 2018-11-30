package com.dzakdzaks.footballapps.features.events.search_events

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
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
import kotlinx.android.synthetic.main.activity_search_events.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity

class SearchEventsActivity : AppCompatActivity(), EventsView {

    private var events: MutableList<Events> = mutableListOf()
    private lateinit var presenter: EventsPresenter
    private lateinit var adapter: EventsAdapter
    val request = ApiRepository()
    val gson = Gson()
    private var onrotation = true

    override fun showLoading() {
        progressBarSearchEvents.visible()
        rvSearchEvents.invisible()
        tvEmpty.invisible()
    }

    override fun hideLoading() {
        progressBarSearchEvents.invisible()
        rvSearchEvents.visible()
    }

    override fun showTeamList(data: List<Events>) {
        events.clear()
        events.addAll(data)
        adapter = EventsAdapter(this, data) {
            ctx.startActivity<EventsDetailActivity>("id" to it.eventId)
        }
        rvSearchEvents.layoutManager = LinearLayoutManager(this)
        rvSearchEvents.setEmptyView(tvEmpty)
        rvSearchEvents.adapter = adapter
        adapter.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_events)

        val query = intent.getStringExtra("query")

        presenter = EventsPresenter(this, request, gson)

        if (onrotation) {
            presenter.getsearchMatch(query)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?
        searchView?.queryHint = "Search Matches"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (onrotation) {
                    presenter.getsearchMatch(newText)
                }
                return false
            }
        })
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        onrotation = false
    }

}
