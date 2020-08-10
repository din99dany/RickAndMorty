package com.example.rickandmorty.retrofitCharacter

import android.telecom.Call
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface ApiClient {

    @GET("character")
    fun getCharacters( @Query("page") page: Int ): Single<CharacterResponse>

    @GET("character/{id}")
    fun getCharacterDetails(@Path("id") id:Int) : Single<CharacterDetails>


    @GET("episode")
    fun getEpisodePage( @Query("page") page: Int ) :  Single<EpisodeResponse>


    @GET("location")
    fun getLocationsPage( @Query("page") page: Int ) : Single<LocationResponse>

    @GET("location/{id}")
    fun getLocationDetails( @Path("id") id:Int ) :  Single<LocationModel>

    @GET("episode/{id}")
    fun getEpisodeDetails( @Path("id") id: Int ): Single<EpisodeModel>
}