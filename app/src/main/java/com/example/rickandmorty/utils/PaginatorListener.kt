package com.example.rickandmorty.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.paginators.PaginatorInterface

class PaginatorListener(val paginator: PaginatorInterface) : RecyclerView.OnScrollListener() {

    private val VISIBLE_THRESHOLD = 1

    override fun onScrolled( recyclerView: RecyclerView, dx: Int, dy: Int ) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
        val lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if ( totalItemCount <= lastVisibleItem + VISIBLE_THRESHOLD ) {
            paginator.updatePaginator()
        }

    }

}