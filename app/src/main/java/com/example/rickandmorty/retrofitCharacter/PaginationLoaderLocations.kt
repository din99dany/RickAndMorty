package com.example.rickandmorty.retrofitCharacter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.processors.PublishProcessor

class PaginationLoaderLocations : ViewModel() {

    var mData: MutableLiveData<List<LocationModel>> = MutableLiveData<List<LocationModel>>()
    private var mPublishProcessor: PublishProcessor<Int> = PublishProcessor.create()
    private var apiServifce: ApiService = ApiService()
    private var pageNumber = 1

    init {
        initOverservables()
    }

    private fun initOverservables() {

        mPublishProcessor
            .onBackpressureBuffer()
            .concatMapSingle { apiServifce.getLocationsPage(it) }
            .retry{ e -> true }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items: List<LocationModel> ->
                    mData.postValue(items)
                },
                { error ->
                    Log.d("mylog",error.message.toString() + ":emit")
                }
            )
    }

    fun updatePaginator() {
        mPublishProcessor.onNext(pageNumber)
        pageNumber++
    }

}