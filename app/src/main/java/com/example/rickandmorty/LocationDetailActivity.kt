package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CenterZoomLayoutManager.CenterZoomLayoutManager
import com.example.rickandmorty.carouselAdapters.CarouselLowCharacter
import com.example.rickandmorty.retrofitmodels.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class LocationDetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarouselLowCharacter
    private lateinit var mName:TextView
    private lateinit var mType: TextView
    private lateinit var mDims: TextView
    private lateinit var mFill: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        findViewById<Button>(R.id.id_button_home_locations).setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        setupComponets()
        setupRecycleView()
        setUpView()

    }

    private fun setupRecycleView() {
        recyclerView = findViewById(R.id.id_location_detail_rv)
        recyclerView.layoutManager = CenterZoomLayoutManager( this,  LinearLayoutManager.HORIZONTAL,false )

        adapter = CarouselLowCharacter(this )
        recyclerView.adapter = adapter
    }

    private fun setUpView() {

        mFill.setImageResource(R.drawable.episode_fill_image)

        if ( intent.extras != null ) {
            val charId = intent.extras?.getInt("location_id") ?: -1
            val apiService = ApiService(applicationContext)

            apiService.getLocationDetails(charId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if( it != null ) {
                            mName.text = it.name
                            mType.text = it.type
                            mDims.text = it.dimension
                            adapter.mData = it.residents
                            recyclerView.smoothScrollBy(2,2)
                        }
                    },
                    { error -> Log.d("mylog",error.message.toString())}
                )

        }
    }

    private fun setupComponets() {
        mName = findViewById(R.id.id_location_name)
        mType = findViewById(R.id.id_location_type)
        mDims = findViewById(R.id.id_location_dims)
        mFill = findViewById(R.id.id_location_detail_filler)
    }
}