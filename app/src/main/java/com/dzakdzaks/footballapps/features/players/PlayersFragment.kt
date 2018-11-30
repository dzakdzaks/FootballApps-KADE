package com.dzakdzaks.footballapps.features.players


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.adapter.PlayersAdapter
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.features.players.player_detail.PlayerDetailActivity
import com.dzakdzaks.footballapps.model.Players
import com.dzakdzaks.footballapps.model.Teams
import com.dzakdzaks.footballapps.utils.invisible
import com.dzakdzaks.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

class PlayersFragment : Fragment(), PlayersView {

    private var listPlayer: MutableList<Players> = mutableListOf()
    lateinit var presenter: PlayersPresenter
    private lateinit var adapter: PlayersAdapter
    val request = ApiRepository()
    val gson = Gson()

    override fun showLoading() {
        progressBarPlayers.visible()
        rvPlayers.invisible()
    }

    override fun hideLoading() {
        progressBarPlayers.invisible()
        rvPlayers.visible()
    }

    override fun showPlayerList(data: List<Players>) {
        refreshPlayers.isRefreshing = false
        listPlayer.clear()
        listPlayer.addAll(data)
        adapter = PlayersAdapter(ctx, listPlayer) {
            ctx.startActivity<PlayerDetailActivity>("id" to it.idPlayer)
        }
        rvPlayers.layoutManager = LinearLayoutManager(this.context)
        rvPlayers.adapter = adapter
        rvPlayers.scrollToPosition(0)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val team: Teams? = arguments?.getParcelable("teams")
        val idTeam = team?.idTeam
        presenter = PlayersPresenter(this, request, gson)
        presenter.getAllPlayers(idTeam)

        refreshPlayers.setOnRefreshListener {
            listPlayer.clear()
            adapter.notifyDataSetChanged()
            presenter.getAllPlayers(idTeam)
        }
    }


}
