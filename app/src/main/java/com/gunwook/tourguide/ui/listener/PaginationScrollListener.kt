package com.gunwook.tourguide.ui.listener

import androidx.recyclerview.widget.RecyclerView
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.recyclerview.widget.LinearLayoutManager

abstract class PaginationScrollListener(layoutManager : LinearLayoutManager) : RecyclerView.OnScrollListener() {

    lateinit var layoutManager : LinearLayoutManager

    init {
        this.layoutManager = layoutManager
    }

    abstract fun loadMoreItems()

    abstract fun isLoading() : Boolean

    abstract fun isLastPage() : Boolean


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.getChildCount()
        val totalItemCount = layoutManager.getItemCount()
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE
            ) {
                loadMoreItems()
            }
        }
    }

}