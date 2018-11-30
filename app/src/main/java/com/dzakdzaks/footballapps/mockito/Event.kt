package com.dzakdzaks.footballapps.mockito

data class Event(
        var intAwayScore: String? = null,
        var intHomeScore: String? = null,
        var strLeague: String? = null,
        var strAwayLineupDefense: String? = null,
        var strHomeLineupDefense: String? = null,
        var strAwayLineupForward: String? = null,
        var strHomeLineupForward: String? = null,
        var strAwayLineupGoalkeeper: String? = null,
        var strHomeLineupGoalkeeper: String? = null,
        var strAwayLineupMidfield: String? = null,
        var strHomeLineupMidfield: String? = null,
        var strAwayTeam: String? = null,
        var strHomeTeam: String? = null,
        var strDate: String? = null,
        var typeEvent: String? = null,
        var idEvent: String? = null
)