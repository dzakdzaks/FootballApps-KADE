package com.dzakdzaks.footballapps.features.events


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.adapter.ViewPagerAdapter
import com.dzakdzaks.footballapps.features.events.next_events.NextEventsFragment
import com.dzakdzaks.footballapps.features.events.past_events.PastEventsFragment
import com.dzakdzaks.footballapps.features.events.search_events.SearchEventsActivity
import kotlinx.android.synthetic.main.fragment_events.*
import org.jetbrains.anko.startActivity


class EventsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(NextEventsFragment(), "Next")
        adapter.addFragment(PastEventsFragment(), "Past")
        viewpagerEvents.adapter = adapter
        tabEvents.setupWithViewPager(viewpagerEvents)
        search_events?.queryHint = "Search matches"
        search_events?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                context?.startActivity<SearchEventsActivity>("query" to query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}
