package com.example.rickandmorty.retrofitCharacter

import android.util.Log
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class ApiService {

    private val apiClient : ApiClient

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        apiClient = retrofit.create(ApiClient::class.java)
    }

    fun getCharacterPage(page: Int) : Single<List<CharacterModel>> {
        return apiClient.getCharacters(page)
            .retry{ e -> true }
            .subscribeOn(Schedulers.io())
            .map { x -> x.characters }
    }

    fun getCharacterDetails(id: Int ) : Single<CharacterDetails> {
        return apiClient.getCharacterDetails(id)
            .retry{ e -> true }
            .subscribeOn(Schedulers.io())
    }

    fun getEpisodePage( page: Int ): Single<List<EpisodeModel>> {
        return apiClient.getEpisodePage(page)
            .retry{ e -> true }
            .subscribeOn(Schedulers.io())
            .map { x -> x.mEpisodes }
    }

    fun getLocationsPage( page: Int ) : Single<List<LocationModel>> {

        return apiClient.getLocationsPage(page)
            .retry{ e -> true }
            .subscribeOn(Schedulers.io())
            .map { x -> x.mLocations }
    }

    fun getLocationDetails( id: Int ): Single<LocationModel> {
        return apiClient.getLocationDetails(id)
            .retry{ e -> true }
            .subscribeOn(Schedulers.io())
    }

    fun getEpisodeDetails( id: Int ): Single<EpisodeModel>  {
        return apiClient.getEpisodeDetails(id)
            .retry{ e -> true }
            .subscribeOn(Schedulers.io())
    }

}