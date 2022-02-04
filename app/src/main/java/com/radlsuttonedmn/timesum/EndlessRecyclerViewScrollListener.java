package com.radlsuttonedmn.timesum;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Created on 3/10/2018.
 * Add new adapter entries when the recyclerView is scrolled to the bottom
 */

abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    // The current offset index of data that is loaded
    private int currentPage = 0;

    // The total number of items in the data set after the last load
    private int previousTotalItemCount = 0;

    // True if still waiting for the last set of data to load.
    private boolean loading = true;

    // Sets the starting page index
    final private int startingPageIndex = 0;

    final private RecyclerView.LayoutManager mLayoutManager;

    EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    // Check if for the previous load has finished.
    @Override
    public void onScrolled(@NonNull RecyclerView view, int dx, int dy) {

        int visibleThreshold = 5;
        int lastVisibleItemPosition;
        int totalItemCount = mLayoutManager.getItemCount();

        lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        // If it’s still loading, check to see if the data set count has
        // changed, if so it has finished loading and it necessary to update the current page
        // number and total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        // If it isn’t currently loading, check to see if there is a need to reload more data.
        // If necessary to reload some more data, execute onLoadMore to fetch the data.
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, view);
            loading = true;
        }
    }

    // Call this method whenever performing new searches
    void resetState() {

        this.currentPage = this.startingPageIndex;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    // Defines the process for actually loading more data based on page
    protected abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}
