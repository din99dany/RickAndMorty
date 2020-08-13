package com.example.rickandmorty.paginators

import android.content.Context
import android.util.Log
import com.example.rickandmorty.retrofitMihaiModels.MihaiLocationModel
import com.example.rickandmorty.retrofitmodels.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class PaginationLoaderLocations( context: Context) : Paginator<MihaiLocationModel>(), PaginatorInterface {

    init {
        apiServifce = ApiService(context)
        initOverservables()
    }

    override fun initOverservables() {

        mPublishProcessor
            .onBackpressureBuffer()
            .concatMapSingle { apiServifce.getLocationsPage(it) }
            .retry{ e -> true }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items: List<MihaiLocationModel> ->
                    mData.postValue(items)
                },
                { error ->
                    Log.d("mylog",error.message.toString() + ":emit")
                }
            )
    }


}