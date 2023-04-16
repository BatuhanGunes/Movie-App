package com.mobillium.movieapp.feature_movie.presentation.movie_list.listeners

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class PaginationScrollListener(private val layoutManager: StaggeredGridLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount

        var pastVisibleItems = 0
        val firstVisibleItems = layoutManager.findFirstVisibleItemPositions(null);
        if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
            pastVisibleItems = firstVisibleItems[0]
        }

        if (!isLoading() && !isLastPage() && visibleItemCount + pastVisibleItems >= totalItemCount && pastVisibleItems >= 0) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}