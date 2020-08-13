package com.example.rickandmorty.paginators

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.retrofitmodels.ApiService
import io.reactivex.rxjava3.processors.PublishProcessor

abstract class Paginator<T> : ViewModel() {

    private var pageNumber = 1
    var mData: MutableLiveData<List<T>> = MutableLiveData<List<T>>()
    var mPublishProcessor: PublishProcessor<Int> = PublishProcessor.create()
    lateinit var apiServifce: ApiService


    abstract fun initOverservables()


    fun updatePaginator() {
        mPublishProcessor.onNext(pageNumber)
        pageNumber++
    }

}