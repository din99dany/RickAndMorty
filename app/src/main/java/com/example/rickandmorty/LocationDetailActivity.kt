package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CenterZoomLayoutManager.CenterZoomLayoutManager
import com.example.rickandmorty.carouselAdapters.CarouselAdapter
import com.example.rickandmorty.retrofitCharacter.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class LocationDetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarouselAdapter
    private lateinit var mName:TextView
    private lateinit var mType: TextView
    private lateinit var mDims: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        setupComponets()
        setupRecycleView()
        setUpView()


    }

    private fun setupRecycleView() {
        recyclerView = findViewById(R.id.id_location_detail_rv)
        recyclerView.layoutManager = CenterZoomLayoutManager( this,  LinearLayoutManager.HORIZONTAL,false )

        adapter = CarouselAdapter(this, listOf())
        recyclerView.adapter = adapter
    }

    private fun setUpView() {
        if ( intent.extras != null ) {
            val charId = intent.extras?.getInt("location_id") ?: -1
            val apiService = ApiService()

            apiService.getLocationDetails(charId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if( it != null ) {
                            mName.text = it.mName
                            mType.text = it.mType
                            mDims.text = it.mDims
                            adapter.mData = it.mResidents
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
    }
}