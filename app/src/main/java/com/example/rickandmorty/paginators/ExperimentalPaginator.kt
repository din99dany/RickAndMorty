package com.example.rickandmorty.paginators

import android.util.Log
import com.example.rickandmorty.retrofitMihaiModels.MihaiCharacterModel
import com.example.rickandmorty.retrofitMihaiModels.MihaiLocationModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single

class ExperimentalPaginator<T>( val dataSource: (page:Int) -> Single<List<T>> ): Paginator<T>(), PaginatorInterface  {

    init {
        initOverservables()
    }

    override fun initOverservables() {

        mPublishProcessor
            .onBackpressureBuffer()
            .concatMapSingle { dataSource(it) }
            .retry{ e -> true }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items: List<T> ->
                    mData.postValue(items)
                },
                { error ->
                    Log.d("mylog",error.message.toString() + ":emit")
                }
            )
    }

}