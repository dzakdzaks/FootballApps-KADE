package com.dzakdzaks.footballapps.features.teams.team_detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.model.Teams
import kotlinx.android.synthetic.main.fragment_overviews.*


class OverviewsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_overviews, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val team: Teams? = arguments?.getParcelable("teams")
        tv_overview_team.text = team?.strDescriptionEN.toString()
    }


}
