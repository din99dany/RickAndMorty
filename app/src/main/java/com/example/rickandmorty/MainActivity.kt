package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.rickandmorty.sessionmanager.SesionManager

class MainActivity : AppCompatActivity() {

    lateinit var mButtonEpisodes: Button
    lateinit var mButtonCharacters: Button
    lateinit var mButtonLocations: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SesionManager.initializaToken()

        setUpButtons()
        setButtonsListeners()
    }

    fun setUpButtons() {
        mButtonEpisodes = findViewById<Button>(R.id.id_button_episodes)
        mButtonCharacters = findViewById<Button>(R.id.id_button_characters)
        mButtonLocations = findViewById<Button>(R.id.id_button_locations)
    }

    fun setButtonsListeners() {
        mButtonEpisodes.setOnClickListener( View.OnClickListener {
            val intentEpisode = Intent(this, EpisodesActivity::class.java )
            startActivity(intentEpisode)
        })

        mButtonCharacters.setOnClickListener( View.OnClickListener {
            val intentEpisode = Intent(this, CharactersActivity::class.java )
            startActivity(intentEpisode)
        })

        mButtonLocations.setOnClickListener( View.OnClickListener {
            val intentEpisode = Intent(this, LocationsActivity::class.java )
            startActivity(intentEpisode)
        })
    }
}