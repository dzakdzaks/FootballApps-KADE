package com.dzakdzaks.footballapps.features.teams


import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.adapter.TeamsAdapter
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.features.teams.team_detail.TeamsDetailActivity
import com.dzakdzaks.footballapps.model.Teams
import com.dzakdzaks.footballapps.utils.invisible
import com.dzakdzaks.footballapps.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textColor


class TeamsFragment : Fragment(), TeamsView {

    private var teams: MutableList<Teams> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    val request = ApiRepository()
    val gson = Gson()
    lateinit var leagueName: String
    var onrotation = true


    override fun showLoading() {
//        activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        progressBarTeams.visible()
        rvTeams.invisible()
        tvEmpty.invisible()
    }

    override fun hideLoading() {
        progressBarTeams.invisible()
        rvTeams.visible()
    }

    override fun showTeamList(data: List<Teams>) {
//        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        refreshTeams.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter = TeamsAdapter(ctx, teams) {
            ctx.startActivity<TeamsDetailActivity>("team" to it)
        }
        rvTeams.layoutManager = LinearLayoutManager(this.context)
        rvTeams.setEmptyView(tvEmpty)
        rvTeams.adapter = adapter
        rvTeams.scrollToPosition(0)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = TeamsPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(R.array.leagueArray)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinnerTeams.adapter = spinnerAdapter

        spinnerTeams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                leagueName = spinnerTeams.selectedItem.toString()

                (p0.getChildAt(0) as TextView).textSize = 20f
                (p0.getChildAt(0) as TextView).textColor = Color.WHITE
                (p0.getChildAt(0) as TextView).textAlignment = View.TEXT_ALIGNMENT_CENTER

                if (onrotation) {
                    when (leagueName) {
                        "English Premier League" -> presenter.getAllTeam("4328")
                        "German Bundesliga" -> presenter.getAllTeam("4331")
                        "Italian Serie A" -> presenter.getAllTeam("4332")
                        "French Ligue 1" -> presenter.getAllTeam("4334")
                        "Spanish La Liga" -> presenter.getAllTeam("4335")
                        "Netherlands Eredivisie" -> presenter.getAllTeam("4337")
                    }
                }

                when (leagueName) {
                    "English Premier League" -> presenter.getAllTeam("4328")
                    "German Bundesliga" -> presenter.getAllTeam("4331")
                    "Italian Serie A" -> presenter.getAllTeam("4332")
                    "French Ligue 1" -> presenter.getAllTeam("4334")
                    "Spanish La Liga" -> presenter.getAllTeam("4335")
                    "Netherlands Eredivisie" -> presenter.getAllTeam("4337")
                }

            }

        }

        search_teams?.queryHint = "Search teams"
        search_teams?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getSearchTeam(newText)
                return false
            }
        })
        search_teams.setOnCloseListener {
            when (leagueName) {
                "English Premier League" -> presenter.getAllTeam("4328")
                "German Bundesliga" -> presenter.getAllTeam("4331")
                "Italian Serie A" -> presenter.getAllTeam("4332")
                "French Ligue 1" -> presenter.getAllTeam("4334")
                "Spanish La Liga" -> presenter.getAllTeam("4335")
                "Netherlands Eredivisie" -> presenter.getAllTeam("4337")
            }
            false
        }

        refreshTeams.setOnRefreshListener {
            teams.clear()
            adapter.notifyDataSetChanged()
            when (leagueName) {
                "English Premier League" -> presenter.getAllTeam("4328")
                "German Bundesliga" -> presenter.getAllTeam("4331")
                "Italian Serie A" -> presenter.getAllTeam("4332")
                "French Ligue 1" -> presenter.getAllTeam("4334")
                "Spanish La Liga" -> presenter.getAllTeam("4335")
                "Netherlands Eredivisie" -> presenter.getAllTeam("4337")
            }
        }


    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        onrotation = false
    }


}
