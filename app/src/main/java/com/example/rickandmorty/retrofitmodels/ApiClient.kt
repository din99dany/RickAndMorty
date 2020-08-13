package com.example.rickandmorty.retrofitmodels

import com.example.rickandmorty.retrofitMihaiModels.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("characters")
    fun getCharacters( @Query("page") page: Int ): Single<List<MihaiCharacterModel>>

    @GET("characters/{id}")
    fun getCharacterDetails(@Path("id") id:Int) : Single<MihaiCharacterModelDetailed>

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GET("episodes")
    fun getEpisodePage( @Query("page") page: Int ) :  Single<List<MihaiEpisodeModel>>


    @GET("episodes/{id}")
    fun getEpisodeDetails( @Path("id") id: Int ): Single<MihaiEpisodeModelDetailed>

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @GET("locations")
    fun getLocationsPage( @Query("page") page: Int ) : Single<List<MihaiLocationModel>>

    @GET("locations/{id}")
    fun getLocationDetails( @Path("id") id:Int ) :  Single<MihaiLocationModelDetailed>

}