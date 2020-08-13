package com.example.rickandmorty.paginators

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PaginatorFactory( val application:Application, val contextApp: Context ) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == PaginationLoaderCharacters::class.java) {
            return PaginationLoaderCharacters(contextApp) as T
        } else if (modelClass == PaginationLoaderEpisodes::class.java) {
            return PaginationLoaderEpisodes(contextApp) as T
        } else if (modelClass == PaginationLoaderLocations::class.java) {
            return PaginationLoaderLocations(contextApp) as T
        } else {
            return super.create(modelClass)
        }
    }
}