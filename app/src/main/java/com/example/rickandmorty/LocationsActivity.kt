package com.example.rickandmorty

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.adapters.RvLocationsAdapter
import com.example.rickandmorty.retrofitCharacter.PaginationLoaderLocations

class LocationsActivity : AppCompatActivity() {
    private lateinit var homeButtom: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RvLocationsAdapter
    private lateinit var paginator: PaginationLoaderLocations
    private val VISIBLE_THRESHOLD = 1


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)

        homeButtom = findViewById<Button>(R.id.id_button_home_locations)
        paginator = ViewModelProviders.of(this).get(PaginationLoaderLocations::class.java)

        setButtonsListeners()
        setupRecycleView()
        setupListenerMore()

    }

    private fun setupListenerMore() {

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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

    private fun setupRecycleView() {
        recyclerView = findViewById<RecyclerView>(R.id.id_locations_rv)
        recyclerView.layoutManager = GridLayoutManager(this,2)

        adapter = RvLocationsAdapter(this   )
        recyclerView.adapter = adapter    }

    fun setButtonsListeners()  {
        homeButtom.setOnClickListener(View.OnClickListener {
            val intentHome = Intent( this, MainActivity::class.java )
            startActivity(intentHome)
        })
    }
}