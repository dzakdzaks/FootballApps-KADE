package com.dzakdzaks.footballapps.features.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.adapter.ViewPagerAdapter
import com.dzakdzaks.footballapps.features.favorites.fav_events.FavoriteEventsFragment
import com.dzakdzaks.footballapps.features.favorites.fav_teams.FavoriteTeamsFragment
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(FavoriteEventsFragment(), "Matches")
        adapter.addFragment(FavoriteTeamsFragment(), "Teams")
        viewpagerFavorites.adapter = adapter
        tabFavorites.setupWithViewPager(viewpagerFavorites)
    }


}
