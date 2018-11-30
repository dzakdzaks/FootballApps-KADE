package com.dzakdzaks.footballapps.features.players.player_detail

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.dzakdzaks.footballapps.R
import com.dzakdzaks.footballapps.api.ApiRepository
import com.dzakdzaks.footballapps.model.Players
import com.dzakdzaks.footballapps.utils.dateFormaterShort
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.content_player_detail.*
import java.text.SimpleDateFormat


class PlayerDetailActivity : AppCompatActivity(), PlayersDetailView {
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun showPlayerDetail(data: List<Players>) {
        player = data[0]
        toolbar_player_detail.title = player.strPlayer
        Glide.with(applicationContext).load(player.strThumb).into(ivPlayerDetail)
        Glide.with(applicationContext).load(player.strCutout).into(imagePlayerDetail)
        tvPlayerNameDetail.text = player.strPlayer
        tvPlayerTeamDetail.text = resources.getString(R.string.player_team) + "   " + player.strTeam
        if (player.dateSigned == null) {
            tvPlayerDateSignedDetail.text = resources.getString(R.string.date_of_signed) + "   -"
        } else {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date = dateFormat.parse(player.dateSigned)
            val formatedDate = dateFormaterShort(date)
            tvPlayerDateSignedDetail.text = resources.getString(R.string.date_of_signed) + "   " + formatedDate
        }
        tvPlayerPositionDetail.text = resources.getString(R.string.position) + "   " + player.strPosition
        if (player.dateBorn == null) {
            tvPlayerDateBornDetail.text = resources.getString(R.string.born) + "   -"
        } else {
            val dateFormat2 = SimpleDateFormat("yyyy-MM-dd")
            val date2 = dateFormat2.parse(player.dateBorn)
            val formatedDate2 = dateFormaterShort(date2)
            tvPlayerDateBornDetail.text = resources.getString(R.string.born) + "   " + formatedDate2
        }
        if (player.strBirthLocation.equals("")) {
            tvPlayerBornLocationDetail.text = resources.getString(R.string.birth_location) + "   -"
        } else {
            tvPlayerBornLocationDetail.text = resources.getString(R.string.birth_location) + "   " + player.strBirthLocation
        }
        if (player.strHeight.equals("") || player.strHeight.equals("0")) {
            tvPlayerHeightDetail.text = resources.getString(R.string.height) + "   -"
        } else {
            tvPlayerHeightDetail.text = resources.getString(R.string.height) + "   " + player.strHeight
        }
        if (player.strWeight.equals("") || player.strWeight.equals("0")) {
            tvPlayerWeightDetail.text = resources.getString(R.string.weight) + "   -"
        } else {
            tvPlayerWeightDetail.text = resources.getString(R.string.weight) + "   " + player.strWeight
        }
        tvPlayerOverviewDetail.text = player.strDescriptionEN

    }

    private lateinit var playersDetailPresenter: PlayersDetailPresenter

    private lateinit var player: Players

    var onrotaion = true

    val request = ApiRepository()
    val gson = Gson()

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        setSupportActionBar(toolbar_player)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar_player.setNavigationOnClickListener { onBackPressed() }

        val data = intent
        id = data.getStringExtra("id")
        Log.d("id player", id)

        playersDetailPresenter = PlayersDetailPresenter(this, request, gson)

        playersDetailPresenter.getPlayerDetail(id)

        if (onrotaion) {
            playersDetailPresenter.getPlayerDetail(id)
        }

        toolbar_player_detail.setExpandedTitleColor(Color.TRANSPARENT)
        toolbar_player_detail.setCollapsedTitleTextColor(Color.WHITE)

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        onrotaion = false
    }
}
