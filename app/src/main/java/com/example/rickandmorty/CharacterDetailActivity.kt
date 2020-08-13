package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CenterZoomLayoutManager.CenterZoomLayoutManager
import com.example.rickandmorty.carouselAdapters.CarouselAdapterEpisode
import com.example.rickandmorty.retrofitmodels.ApiService
import com.example.rickandmorty.utils.DecodingUtils
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var recycleView : RecyclerView
    private lateinit var adapter: CarouselAdapterEpisode
    private lateinit var mThumbainl: ImageView
    private lateinit var mGender: ImageView
    private lateinit var mAlive: ImageView
    private lateinit var mName: TextView
    private lateinit var mLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        findViewById<Button>(R.id.id_button_home_charDetail).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        setupComponents()
        setRecycleView()
        setUpView()

    }

    private fun setupComponents() {
        mThumbainl = findViewById<ImageView>(R.id.id_charDet_image)
        mName = findViewById<TextView>(R.id.id_charDet_name)
        mLocation = findViewById<TextView>(R.id.id_charDet_location)
        mGender = findViewById<ImageView>(R.id.id_charDet_gender)
        mAlive = findViewById<ImageView>(R.id.id_charDet_alive)    }

    fun setRecycleView() {
        recycleView  = findViewById<RecyclerView>(R.id.id_charDet_rv)
        recycleView.layoutManager= CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL,false )

        adapter = CarouselAdapterEpisode(this )
        recycleView.adapter = adapter
    }

    fun setUpView() {

        if ( intent.extras != null ) {
            val charId = intent.extras?.getInt("char_id") ?: -1
            val apiService = ApiService(applicationContext)

            apiService.getCharacterDetails(charId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if( it != null ) {
                            Picasso.get()
                                .load(it.thumbnail)
                                .into(mThumbainl)

                            mName.text = it.name
                            mLocation.text = it.location.name
                            adapter.mData = it.episodes

                            mGender.setImageResource(DecodingUtils.decodeGenderToImage(it.gender))
                            mAlive.setImageResource(DecodingUtils.decodeStatusToImage(it.status))

                            recycleView.smoothScrollBy( 2,2 )

                        }
                    },
                    { error -> Log.d("mylog",error.message.toString())}
                )

        }
    }
}