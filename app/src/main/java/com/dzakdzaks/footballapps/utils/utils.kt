package com.dzakdzaks.footballapps.utils

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

@SuppressLint("SimpleDateFormat")
fun dateFormater(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EEEE, d MMMM yyyy").format(this)
}

@SuppressLint("SimpleDateFormat")
fun dateFormaterShort(date: Date?): String? = with(date ?: Date()) {
    SimpleDateFormat("EE, d MMMM yyyy").format(this)
}

fun toGMTFormat(time: String): Date? {
    val formatter = SimpleDateFormat("dd/MM/yy HH:mm:ss", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = time
    return formatter.parse(dateTime)
}