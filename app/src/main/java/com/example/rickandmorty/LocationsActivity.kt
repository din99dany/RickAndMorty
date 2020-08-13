package com.example.rickandmorty

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.adapters.RvLocationsAdapter
import com.example.rickandmorty.paginators.PaginationLoaderLocations
import com.example.rickandmorty.paginators.PaginatorFactory
import com.example.rickandmorty.utils.PaginatorListener

class LocationsActivity : AppCompatActivity() {
    private lateinit var homeButtom: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RvLocationsAdapter
    private lateinit var paginator: PaginationLoaderLocations


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_locations)



        homeButtom = findViewById<Button>(R.id.id_button_home_locations)
        paginator = ViewModelProviders.of(this, PaginatorFactory(application,this))
            .get(PaginationLoaderLocations::class.java)


        setButtonsListeners()
        setupRecycleView()
        setupListenerMore()

    }

    private fun setupListenerMore() {

        recyclerView.addOnScrollListener(PaginatorListener(paginator))

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
        recyclerView.adapter = adapter
    }

    fun setButtonsListeners()  {
        homeButtom.setOnClickListener(View.OnClickListener {
            val intentHome = Intent( this, MainActivity::class.java )
            startActivity(intentHome)
        })
    }
}