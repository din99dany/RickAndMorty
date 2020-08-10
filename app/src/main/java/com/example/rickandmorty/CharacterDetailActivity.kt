package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.CenterZoomLayoutManager.CenterZoomLayoutManager
import com.example.rickandmorty.carouselAdapters.CarouselAdapter
import com.example.rickandmorty.retrofitCharacter.ApiService
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var recycleView : RecyclerView
    private lateinit var adapter: CarouselAdapter
    private lateinit var mThumbainl: ImageView
    private lateinit var mGender: ImageView
    private lateinit var mAlive: ImageView
    private lateinit var mName: TextView
    private lateinit var mLocation: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        mThumbainl = findViewById<ImageView>(R.id.id_charDet_image)
        mName = findViewById<TextView>(R.id.id_charDet_name)
        mLocation = findViewById<TextView>(R.id.id_charDet_location)
        mGender = findViewById<ImageView>(R.id.id_charDet_gender)
        mAlive = findViewById<ImageView>(R.id.id_charDet_alive)


        setRecycleView()
        setUpView()

    }

    fun setRecycleView() {
        recycleView  = findViewById<RecyclerView>(R.id.id_charDet_rv)
        recycleView.layoutManager= CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL,false )

        adapter =
            CarouselAdapter(
                this,
                listOf<String>()
            )
        recycleView.adapter = adapter
    }

    fun setUpView() {


        if ( intent.extras != null ) {
            val charId = intent.extras?.getInt("char_id") ?: -1
            val apiService = ApiService()

            apiService.getCharacterDetails(charId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if( it != null ) {
                            Picasso.get()
                                .load(it.thumbnail)
                                .into(mThumbainl)

                            mName.text = it.name
                            mLocation.text = it.location?.name ?: ""
                            adapter.mData = it.episodes ?: listOf()

                            when( it.gender ) {
                                "Male" -> mGender.setImageResource(R.drawable.male_icon)
                                "Female" -> mGender.setImageResource(R.drawable.female_icon)
                            }

                            when( it.status ) {
                                "Alive" -> mAlive.setImageResource(R.drawable.ic_alive)
                                "Dead" -> mAlive.setImageResource(R.drawable.ic_deadicon)
                            }

                        }
                    },
                    { error -> Log.d("mylog",error.message.toString())}
                )

        }
    }
}