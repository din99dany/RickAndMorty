package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.adapters.RvEpisodeAdapter
import com.example.rickandmorty.retrofitCharacter.PaginationLoaderEpisodes

class EpisodesActivity : AppCompatActivity() {
    private lateinit var homeButtom: Button
    private lateinit var recycleView: RecyclerView
    private lateinit var adapter: RvEpisodeAdapter
    private lateinit var paginator: PaginationLoaderEpisodes
    private val VISIBLE_THRESHOLD = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)

        homeButtom = findViewById<Button>(R.id.id_button_home_locations)
        paginator = ViewModelProviders.of(this).get( PaginationLoaderEpisodes::class.java )

        setButtonsListeners()
        setupRecycleView()
        setUpLoadMoareListener()
    }

    private fun setupRecycleView() {
        recycleView = findViewById(R.id.id_episodes_rv)
        adapter = RvEpisodeAdapter(this)

        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

    }

    fun setButtonsListeners() {
        homeButtom.setOnClickListener(View.OnClickListener {
            val intentHome = Intent( this, MainActivity::class.java )
            startActivity(intentHome)
        })
    }

    private fun setUpLoadMoareListener() {


        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled( recyclerView: RecyclerView, dx: Int, dy: Int ) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
                val lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if ( totalItemCount <= lastVisibleItem + VISIBLE_THRESHOLD ) {
                    paginator.updatePaginator()
                }

            }
        })

        paginator.mData.observe( this, Observer {
            adapter.mData += it
            adapter.notifyDataSetChanged()
        })

        paginator.updatePaginator()
    }
}