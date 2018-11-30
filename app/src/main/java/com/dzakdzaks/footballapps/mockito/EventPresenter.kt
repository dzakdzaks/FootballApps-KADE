package com.dzakdzaks.footballapps.mockito

import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.coroutines.experimental.bg

class EventPresenter(private val view: EventView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getLastMatch(id: String?) {
        view.showLoading()

        launch(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TSDBAPI.getLastMatch(id)),
                        EventResponse::class.java
                )
            }
            view.showEventList(data.await().events)
            view.hideLoading()
        }
    }
}