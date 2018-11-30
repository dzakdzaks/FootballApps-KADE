package com.dzakdzaks.footballapps.mockito


interface EventView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>)
}