package com.example.rickandmorty.paginators

import android.content.Context
import android.util.Log
import com.example.rickandmorty.retrofitMihaiModels.MihaiCharacterModel
import com.example.rickandmorty.retrofitmodels.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class PaginationLoaderCharacters( context: Context) : Paginator<MihaiCharacterModel>(), PaginatorInterface {

    init {
        apiServifce = ApiService(context)
        initOverservables()
    }

    override fun initOverservables() {
        mPublishProcessor
        .onBackpressureBuffer()
        .concatMapSingle { it -> apiServifce.getCharacterPage(it) }
        .retry{ e -> true }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { items: List<MihaiCharacterModel> ->
                mData.postValue(items)
            },
            { error ->
                Log.d("mylog",error.message.toString() + ":emit")
            }
        )
    }

}