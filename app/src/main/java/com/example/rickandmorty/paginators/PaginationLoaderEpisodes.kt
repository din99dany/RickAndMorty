package com.example.rickandmorty.paginators

import android.content.Context
import android.util.Log
import com.example.rickandmorty.retrofitMihaiModels.MihaiEpisodeModel
import com.example.rickandmorty.retrofitmodels.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class PaginationLoaderEpisodes(context: Context) : Paginator<MihaiEpisodeModel>(), PaginatorInterface {

    init {
        apiServifce = ApiService(context)
        initOverservables()
    }

    override fun initOverservables() {
        mPublishProcessor
            .onBackpressureBuffer()
            .concatMapSingle { apiServifce.getEpisodePage(it) }
            .retry{ e -> true }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items: List<MihaiEpisodeModel> ->
                    mData.postValue(items)
                },
                { error ->
                    Log.d("mylog",error.message.toString() + ":emit")
                }
            )
    }

}