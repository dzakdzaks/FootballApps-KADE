package com.dzakdzaks.footballapps.mockito

import kotlinx.coroutines.experimental.android.UI

open class CoroutineContextProvider {
    open val main: kotlin.coroutines.experimental.CoroutineContext by lazy { UI }
}