package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CenterZoomLayoutManager.CenterZoomLayoutManager
import com.example.rickandmorty.carouselAdapters.CarouselLowCharacter
import com.example.rickandmorty.retrofitmodels.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class EpisodeDetailActivity : AppCompatActivity() {

    private lateinit var mNmae: TextView
    private lateinit var mDate: TextView
    private lateinit var mSeas: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarouselLowCharacter
    private lateinit var mSynopsys: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        findViewById<Button>(R.id.id_button_home_locations).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        setupComponents()
        setupRecycleView()
        setupView()

    }

    private fun setupView() {

        if ( intent.extras != null ) {
            val charId = intent.extras?.getInt("episode_id") ?: -1
            val apiService = ApiService(applicationContext)

            apiService.getEpisodeDetails(charId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if( it != null ) {
                            mNmae.text = it.name
                            mDate.text = it.date
                            mSeas.text = it.episode
                            adapter.mData = it.characters
                            mSynopsys.text = it.synopsis
                            recyclerView.smoothScrollBy(2,2)
                        }
                    },
                    { error -> Log.d("mylog",error.message.toString())}
                )

        }
    }

    private fun setupRecycleView() {
        recyclerView = findViewById(R.id.id_episode_detail_rv)
        recyclerView.layoutManager = CenterZoomLayoutManager( this, LinearLayout.HORIZONTAL, false )

        adapter = CarouselLowCharacter(this )
        recyclerView.adapter = adapter
    }

    private fun setupComponents() {
        mNmae = findViewById(R.id.id_episode_detail_name)
        mDate = findViewById(R.id.id_episode_detail_date)
        mSeas = findViewById(R.id.id_episode_detail_season)
        mSynopsys = findViewById(R.id.id_episode_synopsys)
    }


}