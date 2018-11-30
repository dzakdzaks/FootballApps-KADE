package com.dzakdzaks.footballapps.features.favorites.fav_events


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.adapter.EventsAdapter
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.features.events.events_detail.EventsDetailActivity
import com.dzakdzaks.footballapps.features.favorites.db_sql.database
import com.dzakdzaks.footballapps.features.favorites.db_sql.db_fav_events.Favorite
import com.dzakdzaks.footballapps.model.Events
import com.dzakdzaks.footballapps.utils.invisible
import com.dzakdzaks.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite_events.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class FavoriteEventsFragment : Fragment(), FavoritesEventsView {
    override fun showLoading() {
        progressBarFavoriteEvents.visible()
        rvFavoriteEvents.invisible()
    }

    override fun hideLoading() {
        progressBarFavoriteEvents.invisible()
        rvFavoriteEvents.visible()
    }

    override fun showData(data: List<Events>) {
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private var event: MutableList<Events> = mutableListOf()
    private lateinit var adapter: EventsAdapter
    private lateinit var presenter: FavoritesEventPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_events, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = EventsAdapter(ctx, event) {
            ctx.startActivity<EventsDetailActivity>("id" to it.eventId)
        }

        rvFavoriteEvents.layoutManager = LinearLayoutManager(this.context)
        rvFavoriteEvents.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = FavoritesEventPresenter(this, request, gson)

        showFav()

        refreshFavEvents.setOnRefreshListener {
            event.clear()
            adapter.notifyDataSetChanged()
            showFav()
        }
    }

    private fun showFav() {
        context?.database?.use {
            refreshFavEvents.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val fav = result.parseList(classParser<Favorite>())
            for (favorite in fav) {
                presenter.getFavEvent(favorite.eventId)
            }
        }
    }


}
