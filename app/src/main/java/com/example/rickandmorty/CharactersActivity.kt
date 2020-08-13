package com.example.rickandmorty

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.adapters.RvCharacterAdapter
import com.example.rickandmorty.paginators.PaginationLoaderCharacters
import com.example.rickandmorty.paginators.PaginatorFactory
import com.example.rickandmorty.utils.PaginatorListener

class CharactersActivity : AppCompatActivity() {

    private lateinit var homeButtom: Button
    private lateinit var recycleView : RecyclerView
    private lateinit var adapter: RvCharacterAdapter
    private lateinit var paginator: PaginationLoaderCharacters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        homeButtom = findViewById(R.id.id_button_home_locations)
        paginator = ViewModelProviders.of(this,PaginatorFactory(application,this))
            .get(PaginationLoaderCharacters::class.java)


        setButtonsListeners()
        setRecycleView()
        setUpLoadMoareListener()

    }

    private fun setUpLoadMoareListener() {


        recycleView.addOnScrollListener(PaginatorListener(paginator))

        paginator.mData.observe( this, Observer {
            adapter.mData += it
        })

        paginator.updatePaginator()

    }

    fun setButtonsListeners() {
        homeButtom.setOnClickListener{
            val intentHome = Intent( this, MainActivity::class.java )
            startActivity(intentHome)
        }
    }

    fun setRecycleView() {
        recycleView = findViewById(R.id.id_character_rv)
        recycleView.layoutManager= LinearLayoutManager(this)

        adapter = RvCharacterAdapter(this, listOf() )
        recycleView.adapter = adapter
    }

}