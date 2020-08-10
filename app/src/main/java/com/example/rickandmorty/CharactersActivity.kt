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
import com.example.rickandmorty.retrofitCharacter.PaginationLoaderCharacters
import io.reactivex.rxjava3.disposables.CompositeDisposable

class CharactersActivity : AppCompatActivity() {

    private lateinit var homeButtom: Button
    private lateinit var disposables: CompositeDisposable
    private lateinit var recycleView : RecyclerView
    private lateinit var adapter: RvCharacterAdapter
    private lateinit var paginator: PaginationLoaderCharacters
    val VISIBLE_THRESHOLD = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        homeButtom = findViewById(R.id.id_button_home_locations)
        disposables = CompositeDisposable()
        paginator = ViewModelProviders.of(this).get(PaginationLoaderCharacters::class.java)


        setButtonsListeners()
        setRecycleView()
        setUpLoadMoareListener()

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

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}