package com.dzakdzaks.footballapps.mockito

import com.google.gson.Gson
import kotlinx.coroutines.experimental.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class EventPresenterTest {

    @Mock
    lateinit var view: EventView

    @Mock
    lateinit var gson: Gson

    @Mock
    lateinit var apiRepository: ApiRepository

    lateinit var presenter: EventPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPastEvents() {
        val match: MutableList<Event> = mutableListOf()
        val response = EventResponse(match)
        val league = "4328"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(TSDBAPI.getLastMatch(league)),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.getLastMatch(league)
        Mockito.verify(view).showLoading()
        Mockito.verify(view).showEventList(match)
        Mockito.verify(view).hideLoading()
    }
}