package com.example.rickandmorty.roommodels

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.retrofitMihaiModels.MihaiCharacterModel
import com.example.rickandmorty.retrofitMihaiModels.MihaiEpisodeModel
import com.example.rickandmorty.retrofitMihaiModels.MihaiLocationModel
import io.reactivex.rxjava3.core.Single


@Dao
interface RoomDAO {

    @Query("SELECT * FROM character WHERE PAGE == (:page)")
    fun getLocalPage( page:Int ) : Single<List<MihaiCharacterModel>>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertList(car: List<MihaiCharacterModel>)
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Query("SELECT * FROM episodes WHERE PAGE == (:page)")
    fun getLocalEpisodePage( page:Int ) : Single<List<MihaiEpisodeModel>>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertEpisodes(car: List<MihaiEpisodeModel>)
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Query("SELECT * FROM location WHERE PAGE == (:page)")
    fun getLocalLocationPage( page:Int ) : Single<List<MihaiLocationModel>>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertLocations(car: List<MihaiLocationModel>)



}